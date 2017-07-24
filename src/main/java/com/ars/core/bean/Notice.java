/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: Notice.java 
 * @Prject: ProjectManagement
 * @Package: com.projectmanager.model 
 * @Description: 
 * @author: zw
 * @date: 2017年4月7日 下午7:38:07 
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
 * @ClassName: Notice 
 * @Description: 
 * @author: zw
 * @date: 2017年4月7日 下午7:38:07  
 */
@Entity(name="m_notice")
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int  id;
	private String createtime;//日期
	private String title;//标题
	private String content;//内容
	private String type;//类型，目前只有公告通知
	
	@Column(name="click",columnDefinition="INT default 0")
	private Integer click;//点击量
	
	@Column(name="state",columnDefinition="INT default 1")
	private Integer state;//0:垃圾箱，1：显示,2:未显示
	
	@Column(name="sort",columnDefinition="INT default 0")
	private Integer sort;//设置排序优先级，默认0：按时间排序，1：热门推荐，可以在首页时，展示在最前面。
	
	@ManyToOne(cascade=CascadeType.PERSIST,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;//关联用户
	
	@OneToMany(cascade=CascadeType.MERGE,fetch=FetchType.LAZY,mappedBy="notice")
	@JsonIgnore
	private Set<Document>documents=new HashSet<Document>();//附件信息
	
	@ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
	@JoinTable(name = "m_user_notice", joinColumns = { @JoinColumn(name = "notice_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	@JsonIgnore
	private Set<User>users=new HashSet<User>(15);
	

	public Integer getId() {
		return id;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}



	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	@Transient
	private Integer choice;

	public Integer getChoice() {
		return choice;
	}

	public void setChoice(Integer choice) {
		this.choice = choice;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", type=" + type
				+ ", click=" + click + ", state=" + state + ", sort=" + sort + ", user=" + user + ", document="
				+ documents + "]";
	}
	
	
}
