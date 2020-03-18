package com.hwua.realm;

import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.pojo.Users;
import com.hwua.service.UsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * realm主要就是两个功能：
 * 1. 对登录的用户进行授权
 * 2. 用来做登录认证
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UsersService usersService;
    /**
     * 对当前登录用具进行授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        //crm系统中要获取登录用户的id，根据id去查询角色表和权限表，把当前用户所对应的角色和权限添加到SimpleAuthorizationInfo对象中即可
        //取登录用户信息的方式1：
        Users user = (Users)principals.getPrimaryPrincipal();
        System.out.println("授权....");
        Users users = usersService.queryById(user.getId());
        System.out.println(users);
        List<Role> roles = users.getRoles();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Role role:roles){
            //给当前登录的用户赋予指定的角色
            authorizationInfo.addRole(role.getRoleName());
            for (Permission permission:role.getPermissions()){
                authorizationInfo.addStringPermission(permission.getPermissionName());
            }
        }
        return authorizationInfo;
    }

    /**
     * 对当前登录用具进行身份验证
     * 此方法什么时候调用，调用调用subject对象调用login方法的时候，底层调用的是securityManager对象的login方法，此login方法最终调用realm方法中的doGetAuthenticationInfo 方法
     * @param token 此参数就是我们controller传过来的令牌，UsernamePasswordToken对象
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username=(String)token.getPrincipal();//获取令牌中传过来的用户名
        Users user=null;
        try {
             user = usersService.queryByName(username);//从数据中找到的用户信息
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user==null){
            throw  new UnknownAccountException();
        }

        ByteSource salt = ByteSource.Util.bytes(user.getUsername());//得到salt,salt要不一样
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),salt,super.getName());
        return authenticationInfo;//把此对象返回给Shiro，shiro会拿这个对象去和你subject传过来的token进行密码比对
    }
}
