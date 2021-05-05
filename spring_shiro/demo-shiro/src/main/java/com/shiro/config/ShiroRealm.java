package com.shiro.config;

import com.shiro.bean.Admin;
import com.shiro.bean.Permission;
import com.shiro.common.MyByteSource;
import com.shiro.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义的realm用来实现用户认证和授权
 * 父类AuthenticatingRealm支持用户认证（登录）
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    AdminService adminService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @param authenticationToken 用户身份，这里存放着用户的账号和密码
     * @throws AuthenticationException 如果认证失败 会抛出异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //获取身份信息
        String principal = (String) authenticationToken.getPrincipal();
        Admin admin = adminService.findByUsername(principal);
        if (admin == null) {
            return null;
        }
        System.out.println("查询数据库了+1");
        if (!ObjectUtils.isEmpty(admin)) {
            return new SimpleAuthenticationInfo(
                    admin.getUsername(),// 用户名
                    admin.getPassword(),// 密码
                    new MyByteSource(admin.getSalt()),// salt=username+salt
                    this.getName());// realm name
        }
        return null;
    }

    /**
     * 用户授权的方法 当用户认证通过每次访问需要授权的请求是都需要执行这段代码来完成授权操作
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("//能进入这里说明用户已经通过验证了");
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println(primaryPrincipal);
        //根据身份信息(获取用户账号，根据账号来从数据库中获取数据)
        Admin admin = adminService.findRoleByUsername(primaryPrincipal);
        if (!CollectionUtils.isEmpty(admin.getRoles())) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            System.out.println("查询数据库了+3");
            admin.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                //授权信息
                List<Permission> perms = adminService.findPermissionByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(perms)){
                    perms.forEach(perm->{
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    } );
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }
}
