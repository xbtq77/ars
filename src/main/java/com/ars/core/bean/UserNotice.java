/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: UserNotice.java 
 * @Prject: ProjectManagement
 * @Package: com.projectmanager.model 
 * @Description: 
 * @author: zw
 * @date: 2017年4月8日 下午9:00:05 
 * @version: V1.0   
 */
package com.ars.core.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/** 
 * @ClassName: UserNotice 
 * @Description: 
 * @author: zw
 * @date: 2017年4月8日 下午9:00:05  
 */
@Entity(name="m_user_notice")
public class UserNotice implements Serializable{
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(Integer notice_id) {
		this.notice_id = notice_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Integer getIsSee() {
		return isSee;
	}

	public void setIsSee(Integer isSee) {
		this.isSee = isSee;
	}

	@Id
	private Integer user_id;
	
	@Id
	private Integer notice_id;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="notice_id")
	private Notice notice;
	
	@Column(name="isSee",columnDefinition="INT default 0")
	private Integer isSee;//0:尚未查看，1、查看了

}
