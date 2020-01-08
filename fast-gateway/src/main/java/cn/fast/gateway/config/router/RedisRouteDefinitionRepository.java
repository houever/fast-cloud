package cn.fast.gateway.config.router;

import cn.fast.gateway.service.impl.DynamicRouteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 路由定义信息的定位器，
 * 负责读取路由配置( org.springframework.cloud.gateway.route.RouteDefinition
 * 子类实现类
 *  1.CachingRouteDefinitionLocator -RouteDefinitionLocator包装类， 缓存目标RouteDefinitionLocator 为routeDefinitions提供缓存功能
 *  2.CompositeRouteDefinitionLocator -RouteDefinitionLocator包装类，组合多种 RouteDefinitionLocator 的实现，为 routeDefinitions提供统一入口
 *  3.PropertiesRouteDefinitionLocator-从配置文件(GatewayProperties 例如，YML / Properties 等 ) 读取RouteDefinition
 *  4.DiscoveryClientRouteDefinitionLocator-从注册中心( 例如，Eureka / Consul / Zookeeper / Etcd 等 )读取RouteDefinition
 *  5.RouteDefinitionRepository-从存储器( 例如，内存 / Redis / MySQL 等 )读取RouteDefinition
 * @author hou
 */
@Slf4j
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    @Resource
    private DynamicRouteServiceImpl dynamicRouteService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
            return dynamicRouteService.getRouteDefinitions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return dynamicRouteService.save(route);
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return dynamicRouteService.delete(routeId);
    }
}
