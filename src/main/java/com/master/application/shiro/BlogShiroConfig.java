package com.master.application.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.master.application.shiro.filter.BlogFormAuthenticationFilter;
import com.master.application.shiro.filter.BlogPermissionsAuthorizationFilter;
import com.master.application.shiro.filter.BlogRolesAuthorizationFilter;


@Configuration
public class BlogShiroConfig {
	
	@Bean
	public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {

		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setLoginUrl("/api/login"); // 登录页面
		shiroFilterFactoryBean.setSuccessUrl("/api/login");// 登陆成功页面
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/user/**", "authc");
		filterChainDefinitionMap.put("/manager/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		Map<String, Filter> cumstomfilterMap = new HashMap<>();
		cumstomfilterMap.put("roles", new BlogRolesAuthorizationFilter());
		cumstomfilterMap.put("authc", new BlogFormAuthenticationFilter());
		cumstomfilterMap.put("perms", new BlogPermissionsAuthorizationFilter());

		shiroFilterFactoryBean.setFilters(cumstomfilterMap);

		return shiroFilterFactoryBean;
	}

	public BlogCredentialsMatcher blogCredentialsMatcher() {
		BlogCredentialsMatcher blogMatcher = new BlogCredentialsMatcher();
		return blogMatcher;
	}

	@Bean
	public BlogShiroRealm blogShiroRealm() {
		BlogShiroRealm blogRealm = new BlogShiroRealm();
		blogRealm.setCredentialsMatcher(blogCredentialsMatcher());
		return blogRealm;
	}

	@Bean
	public DefaultWebSecurityManager securityManager() {

		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(blogShiroRealm());
		return securityManager;
	}

	

}
