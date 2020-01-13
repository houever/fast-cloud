package cn.fast.gateway.filter;

import cn.fast.gateway.common.RedisUtil;
import cn.fast.gateway.service.IAuthService;
import cn.fast.web.common.constant.SecurityConstants;
import cn.fast.web.common.model.OAuth2AccessToken;
import cn.fast.web.common.result.Result;
import cn.fast.web.common.utils.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 请求url权限校验
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class AccessGatewayFilter implements GlobalFilter {

    private final static String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private final static String X_CLIENT_TOKEN = "x-client-token";
    /**
     * 由authentication-client模块提供签权的feign客户端
     */

    private final IAuthService authService;
    private final RedisUtil redisUtil;


    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //获取请求头里的token
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        //不需要网关签权的url
        if (authService.ignoreAuthentication(url)) {
            return chain.filter(exchange);
        }
        if (null != authentication) {
            String token = authentication.substring(7);
            if (authService.validToken(token)) {//如果验证token通过
                ServerHttpRequest.Builder builder = request.mutate();
                OAuth2AccessToken oAuth2AccessToken = (OAuth2AccessToken) redisUtil.get(SecurityConstants.USER_TOKEN + token);
                List<String> btns = oAuth2AccessToken.getBtns();
                String userJson = GsonUtil.gson2String(oAuth2AccessToken);
                //如果是 GET 请求，放行
                if (method.equals(HttpMethod.GET.toString())) {
                    builder.header(SecurityConstants.AUTH_HEADER, userJson);
                    return chain.filter(exchange.mutate().request(builder.build()).build());
                }
                //非get请求验证权限
                if (havePermission(btns, url)) {//如果有权限，放行
                    builder.header(SecurityConstants.AUTH_HEADER, userJson);
                    return chain.filter(exchange.mutate().request(builder.build()).build());
                }
                return unauthorized(exchange);
            }
            return invalidtoken(exchange);
        }
        return invalidtoken(exchange);
    }


    public boolean havePermission(List<String> btns, String url) {
        PathMatcher matcher = new AntPathMatcher();
        for (String s : btns) {
            boolean match = matcher.match(s, url);
            log.debug("我的==>      " + s + "     请求:" + url + "         ======>" + match);
            if (match) {
                return match;
            }
        }
        return Boolean.FALSE;
    }

    private Mono<Void> invalidtoken(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    /**
     * 没有权限，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        String json = GsonUtil.gson2String(Result.fail("操作无权限"));
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(json.getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
