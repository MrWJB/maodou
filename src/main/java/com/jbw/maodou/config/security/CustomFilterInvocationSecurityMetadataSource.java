package com.jbw.maodou.config.security;

import com.jbw.maodou.entity.security.Menu;
import com.jbw.maodou.entity.security.Role;
import com.jbw.maodou.mapper.security.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    MenuMapper menuMapper;

    /**
     * 返回当前请求所需要的角色信息。
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求的URL
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //从数据库获取所有的角色所对应的路径信息。如：/db/** -> 数据库管理员
        List<Menu> allMenus = menuMapper.getAllMenus();
        //循环遍历
        for (Menu menu : allMenus) {
            //如果请求的路径与数据中配置的路径匹配上，那么继续执行，否则，返回登录页面
            if (antPathMatcher.match(menu.getPattern(), requestUrl)) {
                //获取所有的角色信息。
                List<Role> roles = menu.getRoles();
                //创建数组，数量与角色的信息数量相同。
                String[] roleArr = new String[roles.size()];
                //循环遍历数组
                for (int i = 0; i < roleArr.length; i++) {
                    //获取角色的名称，放到数组里
                    roleArr[i] = roles.get(i).getName();
                }
                //创建集合，并返回信息。
                return SecurityConfig.createList(roleArr);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
