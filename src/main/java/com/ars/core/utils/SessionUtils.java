package com.ars.core.utils;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
/**
 * 对session的操作工具类
 * @author zw
 *
 */
public class SessionUtils {
	
	public  static Serializable getAttribute(HttpServletRequest request,String name){
		Serializable value = (Serializable) request.getSession().getAttribute(name);
		return value;
	}
	public static void setAttribute(HttpServletRequest request,String name,Serializable value){
		 request.getSession().setAttribute(name, value);
	}
	
	public static void deleteSessionAttribute(HttpServletRequest request,String name){
		request.getSession().removeAttribute(name);
	}

}
