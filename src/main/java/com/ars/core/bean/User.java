package com.ars.core.bean;

import java.sql.Timestamp;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户实体类
 * @author Spring 2017-03-28
 */
@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "account")
	private String account;// 账号
	@Column(name = "password")
	private String password;// 密码
	@Column(name = "username")
	private String username;// 真实姓名
	@Column(name = "mobile")
	private String mobile;// 手机号
	@Column(name = "email")
	private String email;// 电子邮箱
	@Column(name = "status")
	private Integer status;// 状态：0-正常、-1-锁定
	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
	private Timestamp createTime;// 创建时间
	@Column(name = "last_login_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
	private Timestamp lastLoginTime;// 最后登录登录的时间
	@Column(name = "loginNum")
	private Integer loginNum;// 成功登录系统的次数
	@Column(name = "is_admin")
	private Integer isadmin;// 是否是超管理员：0-普通管理员，2-超级管理员
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reportUser")
	private Set<Project> reportProjects = new HashSet<Project>();//申报的项目
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "approveUser")
	private Set<Project> approvedProjects = new HashSet<Project>();//审批的项目
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "m_user_message", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "message_id") })
	@JsonIgnore
	private Set<Message> messages = new HashSet<Message>();//我的消息
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "m_user_notice", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "notice_id") })
	@JsonIgnore
	private Set<Notice> notices = new HashSet<Notice>();//通知公告

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}



	public Set<Project> getReportProjects() {
		return reportProjects;
	}

	public void setReportProjects(Set<Project> reportProjects) {
		this.reportProjects = reportProjects;
	}

	public Set<Project> getApprovedProjects() {
		return approvedProjects;
	}

	public void setApprovedProjects(Set<Project> approvedProjects) {
		this.approvedProjects = approvedProjects;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<Notice> getNotices() {
		return notices;
	}

	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", status=" + status + "]";
	}
}
