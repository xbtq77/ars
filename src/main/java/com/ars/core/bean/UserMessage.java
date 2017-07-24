/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: UserMessage.java 
 * @Prject: ProjectManagement
 * @Package: com.projectmanager.model 
 * @Description: 
 * @author: zw
 * @date: 2017年4月8日 下午8:59:44 
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
 * @ClassName: UserMessage 
 * @Description: 
 * @author: zw
 * @date: 2017年4月8日 下午8:59:44  
 */
@Entity(name="m_user_message")
public class UserMessage  implements Serializable {
	@Id
	private Integer user_id;
	
	@Id
	private Integer message_id;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="message_id")
	private Message message;
	
	@Column(name="isSee",columnDefinition="INT default 0")
	private Integer isSee;//0:尚未查看，1、查看了

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getIsSee() {
		return isSee;
	}

	public void setIsSee(Integer isSee) {
		this.isSee = isSee;
	}
	
	
}
