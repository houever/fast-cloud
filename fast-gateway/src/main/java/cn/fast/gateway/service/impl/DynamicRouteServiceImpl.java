package cn.fast.gateway.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {
    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher publisher;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RestTemplate restTemplate;
    private Map<String, RouteDefinition> routeDefinitionMaps = new ConcurrentHashMap<String, RouteDefinition>();

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    //增加路由
    public String add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    //更新路由
    public String update(RouteDefinition definition) {
        try {
            delete(definition.getId());
        } catch (Exception e) {
            return "update fail,not find route  routeId: " + definition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception e) {
            return "update route  fail";
        }
    }

    //删除路由
    public Mono<ResponseEntity<Object>> delete(String id) {
        return this.routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(() -> {
            return Mono.just(ResponseEntity.ok().build());
        })).onErrorResume((t) -> {
            return t instanceof NotFoundException;
        }, (t) -> {
            return Mono.just(ResponseEntity.notFound().build());
        });
    }


    private void loadRouteDefinition() throws Exception {
        //从redis中取出 路由配置
//        Set<String> gatewayKeys = stringRedisTemplate.keys(RouterConstants.GATEWAY_ROUTES + "*");
//        log.info("从redis取出 gatewayKeys====>{}", gatewayKeys);
//        if (CollectionUtils.isEmpty(gatewayKeys)) {
//            ResponseEntity<Result> forEntity = restTemplate.getForEntity("http://localhost:9001/system/router/all", Result.class);
//            Result<List<LinkedHashMap>> result = forEntity.getBody();
//            log.info("从数据库取出的路由：{}", forEntity);
//            //远程从数据库中读取路由配置信息
//            if (result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
//                String s = GsonUtil.gson2String(result.getData());
//                List<GatewayRouteDefinition> gatewayRouteDefinitions = GsonUtil.json2List(s, GatewayRouteDefinition.class);
//                //RouterConstants.gatewayRouteDefinitions = gatewayRouteDefinitions;
//                log.info("从mysql取出 gatewayRouteDefinitions====>{}", gatewayRouteDefinitions);
//                if (null != gatewayRouteDefinitions && gatewayRouteDefinitions.size() > 0) {
//                    for (GatewayRouteDefinition gatewayRouteDefinition : gatewayRouteDefinitions) {
//                        routeDefinitionMaps.put(gatewayRouteDefinition.getId(), RouteDefinitionUtil.assembleRouteDefinition(gatewayRouteDefinition));
//                        stringRedisTemplate.opsForValue().set(RouterConstants.GATEWAY_ROUTES + gatewayRouteDefinition.getId(), GsonUtil.gson2String(gatewayRouteDefinition));
//                    }
//                }
//                return;
//            }
//            return;
//        }

//        List<String> gatewayRoutes = Optional.ofNullable(stringRedisTemplate.opsForValue().multiGet(gatewayKeys)).orElse(Lists.newArrayList());
//        log.debug("从redis取出的gatewayRoutes====>{}", gatewayRoutes);
//        gatewayRoutes.forEach(value -> {
//            try {
//                RouteDefinition routeDefinition = new ObjectMapper().readValue(value, RouteDefinition.class);
//                log.debug("routeDefinition====>{}", routeDefinition);
//                routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        });
    }

    public Flux<RouteDefinition> getRouteDefinitions() throws Exception {
        //loadRouteDefinition();
        return Flux.fromIterable(routeDefinitionMaps.values());
    }

    public Mono<Void> save(Mono<RouteDefinition> routeDefinitionMono) {
        return routeDefinitionMono.flatMap(routeDefinition -> {
            routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
            return Mono.empty();
        });
    }

    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeDefinitionMaps.remove(id);
            return Mono.empty();
        });
    }

    /**
     * 将map集合中的数据转化为指定对象的同名属性中
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T bean = clazz.newInstance();
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

}
