/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: Document.java 
 * @Prject: ProjectManagement
 * @Package: com.projectmanager.model 
 * @Description: 
 * @author: zw
 * @date: 2017年4月7日 下午7:56:27 
 * @version: V1.0   
 */
package com.ars.core.bean;

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
 * @ClassName: Document 
 * @Description: 
 * @author: zw
 * @date: 2017年4月7日 下午7:56:27  
 */
@Entity(name="m_document")
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	
	private String url;
	
	private String date;
	
	public Document() {
		super();
	}


	private String name;
	
	private String type;
	
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="notice_id")
	private Notice notice;
	
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="message_id")
	private Message message;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Notice getNotice() {
		return notice;
	}


	public void setNotice(Notice notice) {
		this.notice = notice;
	}


	public Message getMessage() {
		return message;
	}


	public Document(int id) {
		super();
		this.id = id;
	}


	public void setMessage(Message message) {
		this.message = message;
	}
	
	

}
