package com.ars.common.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ars.core.bean.User;
import com.ars.core.service.UserService;

@Component("loginRealm")
public class LoginRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

/*	@Autowired
	private ActionService actionService;*/

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
		DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
		Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
		for (Session session : sessions) {
			String loginusername = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
//			if (username.equals(loginusername)) {
//				throw new ConcurrentAccessException("该用户已经登录，请重新确认后再登录！");
//				
//			}
		}
		User u=new User();
		u.setUsername(username);
		u.setEmail(username);
		User user = userService.findByNameOrEmail(u);
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "loginRealm");
		return authcInfo;
	/*	User user = userService.findByNameOrEmail(user);
		if (user == null) {
			throw new UnknownAccountException("该用户名称不存在！");
		}else if ("1".equals(user.getStatus())) {  
			throw new LockedAccountException("账号被锁定，无法登录！");  
		}else if (password.equals(user.getPassword())) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "loginRealm");
			return authcInfo;
		}else {
		    throw new IncorrectCredentialsException("密码错误！");
		}*/
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		String username = (String) principals.getPrimaryPrincipal();
		//User user = userService.getByName(username);
		Set<String> set = new HashSet<String>();
		try {
		/*	List<Action> allActions = userService.getByUser(user);
			
			if (allActions != null) {
				for (Action act : allActions) {
					set.add(act.getFlag());
				}
			}*/
			auth.addStringPermissions(set);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auth;
	}
	
	// 修改权限后刷新缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
