package com.ars.core.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目实体类
 * @author Spring 2017-03-28
 */
@Entity(name = "p_project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "project_no")
	private String projectNo;//项目编号
	@Column(name = "project_type")
	private String projectType;//项目类型
	@Column(name = "project_name")
	private String projectName;//项目名称
	@Column(name = "contact_person")
	private String contactPerson;  //项目联系人
	@Column(name = "owner_company")
	private String ownerCompany; //业主单位名称
	@Column(name = "legal_person")
	private String legalPerson; //法人代表
	@Column(name = "telephone")
	private String telephone; //办公电话
	@Column(name = "mobile")
	private String mobile;//手机
	@Column(name = "email")
	private String email; //电子邮箱
	@Column(name = "bulid_company")
	private String bulidCompany; //项目建设单位
	@Column(name = "bulid_nature")
	private String bulidNature; //项目建设性质
	@Column(name = "bulid_area")
	private String bulidArea;//项目建设所在地区名
	@Column(name = "bulid_address")
	private String bulidAddress;//项目建设地址
	@Column(name = "planning_area")
	private String planningArea; //规划面积
	@Column(name = "total_investment")
	private Float totalInvestment;//总投资金额
	@Column(name = "complate_investment")
	private Float complateInvestment;//累计完成投资
	@Column(name = "last_year_investment")
	private Float lastYearInvestment; //去年完成投资
	@Column(name = "this_year_investment")
	private Float thisYearInvestment;//今年计划投资
	@Column(name = "project_status")
	private String projectStatus;//项目状态
	@Column(name = "start_time")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+08")
	private Timestamp startTime;//项目开工时间
	@Column(name = "end_time")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+08")
	private Timestamp endTime;//项目竣工时间
	@Column(name = "plan_start_time")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+08")
	private Timestamp planStartTime; //计划开工时间
	@Column(name = "plan_end_time")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+08")
	private Timestamp planEndTime;//计划竣工时间
	@Column(name = "description", length = 1000)
	private String description; //建设内容和规模
	@Column(name = "progress", length = 1000)
	private String progress; //项目进展情况
	@Column(name = "existing_problem", length = 1000)
	private String existingProblem; //存在的问题
	@Column(name = "remark", length = 1000)
	private String remark;//备注
	@Column(name = "report_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
	private Timestamp reportTime;//申报时间
	@Column(name = "approve_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
	private Timestamp approveTime; //项目审批时间
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "report_user")
	private User reportUser;//项目申报人
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approve_user")
	private User approveUser;//项目审批人
	@Column(name = "approve_status")
	private Integer approveStatus;
	@Column(name = "status")
	private Integer status;//项目的流程状态，0-预申报、1-已申报、2-已审批、3-计划中、4-已实施、5、已完成

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getOwnerCompany() {
		return ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getBulidCompany() {
		return bulidCompany;
	}

	public void setBulidCompany(String bulidCompany) {
		this.bulidCompany = bulidCompany;
	}

	public String getBulidNature() {
		return bulidNature;
	}

	public void setBulidNature(String bulidNature) {
		this.bulidNature = bulidNature;
	}

	public String getBulidArea() {
		return bulidArea;
	}

	public void setBulidArea(String bulidArea) {
		this.bulidArea = bulidArea;
	}

	public String getBulidAddress() {
		return bulidAddress;
	}

	public void setBulidAddress(String bulidAddress) {
		this.bulidAddress = bulidAddress;
	}

	public String getPlanningArea() {
		return planningArea;
	}

	public void setPlanningArea(String planningArea) {
		this.planningArea = planningArea;
	}

	public Float getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(Float totalInvestment) {
		this.totalInvestment = totalInvestment;
	}

	public Float getComplateInvestment() {
		return complateInvestment;
	}

	public void setComplateInvestment(Float complateInvestment) {
		this.complateInvestment = complateInvestment;
	}

	public Float getLastYearInvestment() {
		return lastYearInvestment;
	}

	public void setLastYearInvestment(Float lastYearInvestment) {
		this.lastYearInvestment = lastYearInvestment;
	}

	public Float getThisYearInvestment() {
		return thisYearInvestment;
	}

	public void setThisYearInvestment(Float thisYearInvestment) {
		this.thisYearInvestment = thisYearInvestment;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Timestamp planStartTime) {
		this.planStartTime = planStartTime;
	}

	public Timestamp getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Timestamp planEndTime) {
		this.planEndTime = planEndTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getExistingProblem() {
		return existingProblem;
	}

	public void setExistingProblem(String existingProblem) {
		this.existingProblem = existingProblem;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getReportTime() {
		return reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public Timestamp getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Timestamp approveTime) {
		this.approveTime = approveTime;
	}

	public User getReportUser() {
		return reportUser;
	}

	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}

	public User getApproveUser() {
		return approveUser;
	}

	public void setApproveUser(User approveUser) {
		this.approveUser = approveUser;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
