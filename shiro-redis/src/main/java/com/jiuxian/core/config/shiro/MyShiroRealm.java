package com.jiuxian.core.config.shiro;

import com.jiuxian.base.user.entity.User;
import com.jiuxian.base.user.service.UserService;
import com.jiuxian.common.utils.ConstantUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return super.supports(token);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        user.getRoleSet().forEach(role -> {
            authorizationInfo.addRole(role.getCode());
            role.getPermissionSet().forEach(permission -> {
                authorizationInfo.addStringPermission(permission.getCode());
            });
        });
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = (String) token.getPrincipal();
        User user = userService.findByAccount(account);
        if (user == null) {
            return null;
        }
        if (ConstantUtils.UserStatus.DISABLE.equals(user.getStatus())) {
            throw new DisabledAccountException();
        } else if (ConstantUtils.UserStatus.FREEZE.equals(user.getStatus())) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName());
        return authenticationInfo;
    }
}
