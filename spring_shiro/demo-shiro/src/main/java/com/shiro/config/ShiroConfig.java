package com.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    //1.创建过滤器的配置bean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给Filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/");//没有登陆的用户只能访问登陆页面
        shiroFilterFactoryBean.setSuccessUrl("/index");//登录成功后要跳转的链接
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");//配置没有权限时跳转的请求地址
        /**
         * 配置权限拦截规则
         */
        LinkedHashMap<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/statics/**","anon");//配置静态资源登陆请求不需要认证
        filterChainMap.put("/login","anon");//配置登陆请求不需要认证 anon表示这个请求不需要认证
        filterChainMap.put("/register","anon");
        /*filterChainMap.put("/logout","logout");//配置登出请求
        filterChainMap.put("/admin/**","authc,roles[admin]");//配置admin开头的所以请求需要登陆 authc表示这个请求需要认证
        filterChainMap.put("/user/**","authc");//配置user开头的所以请求需要登陆 authc表示这个请求需要认证*/
        filterChainMap.put("/**","authc");//配置剩余的所有请求都需要进行登陆认证（注意：这个必须写在最后）
        //配置权限拦截规则:生效
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        System.out.println("三叉拦截");
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public SecurityManager securityManager(Realm shiroRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //设置realm.
        securityManager.setRealm(shiroRealm);
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    //3.身份认证realm; (这个需要自己写，账号密码校验；权限等)
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1023);
        shiroRealm.setCredentialsMatcher(credentialsMatcher);

        return shiroRealm;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("192.168.2.180");
        redisManager.setPort(6379);
        redisManager.setExpire(60);//设置过期时间
        redisManager.setTimeout(0);
        return redisManager;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 开启shiro注解支持 DefaultAdvisorAutoProxyCreator
     * 需要借助spring的aop支持 AuthorizationAttributeSourceAdvisor
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 使授权注解起作用,开启aop支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
