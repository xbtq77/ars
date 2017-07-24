/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: Fedback.java 
 * @Prject: ProjectManagement
 * @Package: com.projectmanager.model 
 * @Description: 
 * @author: zw
 * @date: 2017年4月8日 下午2:03:15 
 * @version: V1.0   
 */
package com.ars.core.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** 
 * @ClassName: Fedback 
 * @Description: 
 * @author: zw
 * @date: 2017年4月8日 下午2:03:15  
 */
@Entity(name="m_fedback")
public class Fedback {
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Fedback getParent() {
		return parent;
	}

	public void setParent(Fedback parent) {
		this.parent = parent;
	}
	
	public Set<Fedback> getFedbacks() {
		return fedbacks;
	}

	public void setFedbacks(Set<Fedback> fedbacks) {
		this.fedbacks = fedbacks;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsSee() {
		return isSee;
	}

	public void setIsSee(Integer isSee) {
		this.isSee = isSee;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String content;  //留言内容
	
	private String createtime;   //留言日期
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user; //留言用户
	

	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="feduser_id")
	private User fedUser;
	
	public User getFedUser() {
		return fedUser;
	}

	public void setFedUser(User fedUser) {
		this.fedUser = fedUser;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	@JsonIgnore
	private Fedback parent; //父节点
	
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="parent")
	@JsonIgnore
	private Set<Fedback> fedbacks=new HashSet<Fedback>(10);  //父节点 分为二级 ，父节点为空为一级，和fedback为二级,这里不确定
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="message_id")
	@JsonIgnore
	private  Message  message;//关联我的消息
	
	@Column(name="state",columnDefinition="INT default 0")
	private Integer state;   //0:不公开，1：公开。
	
	@Column(name="isSee",columnDefinition="INT default 0")
	private Integer isSee;//0:尚未查看，1、查看了
	

}
