package cn.fast.auth.config;

import cn.fast.auth.converter.CustJwtAccessTokenConverter;
import cn.fast.auth.handler.CustomAccessDeniedHandler;
import cn.fast.auth.handler.CustomAuthEntryPoint;
import cn.fast.auth.handler.CustomWebResponseExceptionTranslator;
import cn.fast.auth.service.MyUserDetailsService;
import cn.fast.auth.strore.MyRedisTokenStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *  cn.fast.admin.cloud.oauth2server.config
 * @Description: 认证服务器
 * @date 2018/4/12 15:16
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Resource
    private CustomAuthEntryPoint customAuthEntryPoint;
    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Resource
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    //授权端点开放
    //isAuthenticated()
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启/oauth/token_key验证端口无权限访问
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                .authenticationEntryPoint(customAuthEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);
        log.info("================AuthorizationServerSecurityConfigurer 已启动================");
    }

    /**
     * 配置客户端详情信息(Client Details)
     * clientId：（必须的）用来标识客户的Id。
     * secret：（需要值得信任的客户端）客户端安全码，如果有的话。
     * scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
     * authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
     * authorities：此客户端可以使用的权限（基于Spring Security authorities）。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
        log.info("======================ClientDetailsServiceConfigurer 已启动=============================");
    }

    /**
     * 配置授权、令牌的访问端点和令牌服务
     * tokenStore：采用redis储存
     * authenticationManager:身份认证管理器, 用于"password"授权模式
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(myUserDetailsService)
                .tokenServices(tokenServices())
                .reuseRefreshTokens(false)
                .exceptionTranslator(customWebResponseExceptionTranslator);

        log.info("===============AuthorizationServerEndpointsConfigurer已启动=============");
    }


    /**
     * redis存储方式
     *
     * @return
     */
    @Bean(name = "redisTokenStore")
    public TokenStore redisTokenStore() {
        TokenStore tokenStore = new MyRedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

//   @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(jwtTokenEnhancer());
//    }


    /**
     * 客户端信息配置在数据库
     *
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 采用RSA加密生成jwt
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        //令牌加密存储工程，用于读取秘钥
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mycloud-jwt.jks"), "123456".toCharArray());
        //自定义jwt访问令牌转换器
        CustJwtAccessTokenConverter tokenConverter = new CustJwtAccessTokenConverter();
        //非对称加密，但jwt长度过长
        //tokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("mycloud-jwt"));
        //对称加密
        tokenConverter.setSigningKey("123456");
        return tokenConverter;
    }
    /**
     * 配置生成token的有效期以及存储方式（此处用的redis）
     *
     * @return
     */
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(redisTokenStore());
        defaultTokenServices.setTokenEnhancer(jwtTokenEnhancer());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1));
        defaultTokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1));
        return defaultTokenServices;
    }
}
