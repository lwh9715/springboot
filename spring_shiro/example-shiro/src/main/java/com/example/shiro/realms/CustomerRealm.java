package com.example.shiro.realms;

import com.example.bean.Permission;
import com.example.bean.User;
import com.example.service.UserService;
import com.example.shiro.salt.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
public class CustomerRealm extends AuthorizingRealm {

    @Resource
    UserService userService;
    /**
     * 用户授权的方法 当用户认证通过每次访问需要授权的请求是都需要执行这段代码来完成授权操作
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findRoleByUsername(primaryPrincipal);
        System.out.println("用户获取权限-----doGetAuthorizationInfo");
        //授权角色信息
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role->{
                simpleAuthorizationInfo.addRole(role.getName());
                //权限信息
                List<Permission> permission = userService.findPermissionByRoleId(role.getId());
                System.out.println("查询数据库了+3");
                if(!CollectionUtils.isEmpty(permission)){
                    permission.forEach(perm->{
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }
    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @param authenticationToken 用户身份，这里存放着用户的账号和密码
     * @throws AuthenticationException 如果认证失败 会抛出异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //根据身份信息
        String principal = (String) authenticationToken.getPrincipal();
        System.out.println("用户获取认证-----doGetAuthorizationInfo");
        User user = userService.findByUsername(principal);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),
                    new MyByteSource(user.getSalt()),
                    this.getName());
        }
        return null;
    }
}
