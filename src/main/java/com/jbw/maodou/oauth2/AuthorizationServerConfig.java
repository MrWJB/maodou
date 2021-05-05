package com.jbw.maodou.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 自定义AuthorizationServerConfig继承AuthorizationServerConfigurerAdapter，完成对授权服务器的配置，然后通过@EnableAuthorizationServer注解
 * 开启授权服务器。
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 该对象将用来支持password模式。
     */
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 该对象将用来完成redis缓存，将令牌信息存储到redis缓存中。
     */
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * 该对象将为刷新token提供支持。
     */
    @Autowired
    UserDetailsService userDetailsService;

    @Bean(value = "passwordEncoderOauth2")
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * authorizedGrantTypes表示OAuth2中的授权模式为refresh_token, Spring security 的实现中将其归为一种，因此如果要实现access_token的刷新，就需要添加这样一种授权模式。
     * accessTokenValiditySeconds 配置了access_token的过期时间；
     * resourceIds 配置了资源的ID；
     * secret 方法配置了加密后的密码，明文123；
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
        .withClient("password")
        .authorizedGrantTypes("password", "refresh_token")
        .accessTokenValiditySeconds(1800)
        .resourceIds("rid")
        .scopes("all")
        .secret("$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq");
    }

    /**
     * 配置了令牌的存储
     * authenticationManager 和 userDetailsService主要用于支持password模式以及令牌的刷新。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    /**
     * 配置表示支持client_id 和 client_secret做登录验证。
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}