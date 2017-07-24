/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: News.java 
 * @Prject: ProjectManagement
 * @Package: com.projectmanager.model 
 * @Description: 
 * @author: zw
 * @date: 2017年4月7日 下午8:12:55 
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


/** 
 * @ClassName: News 
 * @Description: 
 * @author: zw
 * @date: 2017年4月7日 下午8:12:55  
 */
@Entity(name="m_message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String createtime;//日期
	private String title;//标题
	private String content;//内容
	private String type;//类型，该类型表示个人消息
	
	@Column(name="state",columnDefinition="INT default 1")
	private Integer state;//0:垃圾箱，1直接发布，2、草稿箱
	
	@Column(name="is_response",columnDefinition="INT default 1")
	private Integer isResponse;//是否签收， 改成是否能够恢复
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;//关联用户
	
	@ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinTable(name = "m_user_message", joinColumns = { @JoinColumn(name = "message_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	@JsonIgnore
	private  Set<User> users=new HashSet<User>(10);//将消息发送给多个人
	

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="message")
	@JsonIgnore
	private Set<Fedback>fedbacks=new HashSet<Fedback>(16);
	
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="message")
	@JsonIgnore
	private Set<Document>documents=new HashSet<Document>();//附件信息
	
	@Transient
	private String choice;
	
	@Transient
	private String mesType;//新消息还是有回复了

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}



	public Integer getIsResponse() {
		return isResponse;
	}

	public void setIsResponse(Integer isResponse) {
		this.isResponse = isResponse;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Fedback> getFedbacks() {
		return fedbacks;
	}

	public void setFedbacks(Set<Fedback> fedbacks) {
		this.fedbacks = fedbacks;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	
}
