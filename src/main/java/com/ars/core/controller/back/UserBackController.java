package com.ars.core.controller.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.SessionFactory;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ars.common.Contants;
import com.ars.core.bean.User;
import com.ars.core.service.UserService;
import com.ars.core.utils.Md5PwdUtil;
import com.ars.core.utils.SessionUtils;

@Controller
@RequestMapping("userBack")
public class UserBackController {
	@Autowired
	private UserService userService;
	@RequestMapping("toLogin.do")
	public String toLogin(){
		
		return "login";
	}
	
	@RequestMapping("login.do")
	public String login(User user,HttpServletRequest request,ModelMap model){
	/*	User u=userService.findByNameOrEmail(user);
		String message="你的用户名或密码错误";
		if(u!=null){
			if((Md5PwdUtil.encode(user.getPassword()).equals(u.getPassword()))){
				SessionUtils.setAttribute(request, Contants.USER, u);
				return "index";
			}
		}
		model.addAttribute("message", message);*/
		
		//获取服务端传过来的信息,单点登录成功后，获取用户名
		
		AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
		String loginName = principal.getName(); 
		user.setUsername(loginName);
		User u=userService.findByNameOrEmail(user);
		
		
		 //登录成功后，添加shiro权限
		 Subject subject = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken(u.getUsername(), u.getPassword());
		 subject.login(token);
		 
		 
         System.out.println(loginName);
		return "index";
	}
	@RequestMapping("findUser.do")
	public String findUser(User user,ModelMap model){
		
	//	List<User>list=userService.findUserByCondition(user);
		model.addAttribute("user", user);
		//model.addAttribute("users", list);
		
		return "/user/userList";
	}
	

}
