package com.aaa.backsystem.shiro;

import com.aaa.backsystem.entity.User;
import com.aaa.backsystem.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * fileName:MyRealm
 * description:
 * author:gyc
 * createTime:2020/7/20 19:50
 * version:1.0.0
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从令牌获取用户名信息
        String userName = authenticationToken.getPrincipal()+"";
        //根据用户名查找
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("login_name",userName);
        User user = userService.selectOne(wrapper);
        //是否查找到
        if (user==null){
            //抛出账号异常
            throw new AccountException();
        }

        //从数据库取出密码和盐值进行验证
        String password = user.getPassword();
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());
        return new SimpleAuthenticationInfo(user,password,salt,getName());
    }
}
