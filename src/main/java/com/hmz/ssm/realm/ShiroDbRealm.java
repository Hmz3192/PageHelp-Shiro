package com.hmz.ssm.realm;

import com.hmz.ssm.model.Admin;
import com.hmz.ssm.service.AdminService;
import com.hmz.ssm.util.CipherUtil;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SimpleAliasRegistry;

import javax.crypto.Cipher;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/7/11.
 */
public class ShiroDbRealm extends AuthorizingRealm{
    private static final String ALGORITHM = "MD5";

    @Autowired
    AdminService adminService;

    public ShiroDbRealm() {
        super();
    }

    /*验证登陆*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(token.getUsername());
        Admin admin = adminService.findUserByName(token.getUsername());
        System.out.println("用户信息："+admin.getUsername()+"密码："+admin.getPassword());
        /*加密过程*/
        CipherUtil cipherUtil = new CipherUtil();
        if (admin != null) {
            return new SimpleAuthenticationInfo(admin.getUsername(), cipherUtil.generatePassword(admin.getPassword()), getName());
        }else {
            throw new AuthenticationException();
        }
    }

    /*登陆成功后进行角色权限验证*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection){
        /*根据username使用role和permission的service层判断，并做对应的权限进来*/
        Set<String> roleNames = new HashSet<String>();
        Set<String> permissions = new HashSet<String>();
        /*添加角色，对应到index.jsp*/
        roleNames.add("admin");
        roleNames.add("administrator");
        /*添加权限，对应到index.jsp*/
        permissions.add("create");
        permissions.add("login.do?main");
        permissions.add("login.do?logout");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }
    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
