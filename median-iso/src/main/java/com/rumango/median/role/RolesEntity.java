package com.rumango.median.role;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_new")
public class RolesEntity {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Id
	@Column(unique = true, nullable = false, name = "role_name")
	private String roleName;

	@Column(name = "screen_name")
	private String screenName;

	@Column(name = "screen_number")
	private int screenNumber;

	@Column(name = "role_conditions")
	private String roleConditions;

	@Column(name = "maker_id")
	private String maker;

	@Column(name = "verifier_id")
	private String verifierId;

	@Column(name = "maker_dt_stamp")
	private Date makerDtStamp;

	@Column(name = "checker_dt_stamp")
	private Date checkerDtStamp;

	@Column(name = "record_status")
	private String recordStatus;

	@Column(name = "auth_status")
	private String authStatus;

	@Column(name = "frist_time_auth")
	private String fristTimeAuth;

	@Column(name = "role_desc")
	private String roleDesc;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "version_no")
	private int versionNo;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(int screenNumber) {
		this.screenNumber = screenNumber;
	}

	public String getRoleConditions() {
		return roleConditions;
	}

	public void setRoleConditions(String roleConditions) {
		this.roleConditions = roleConditions;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getVerifierId() {
		return verifierId;
	}

	public void setVerifierId(String verifierId) {
		this.verifierId = verifierId;
	}

	public Date getMakerDtStamp() {
		return makerDtStamp;
	}

	public void setMakerDtStamp(Date makerDtStamp) {
		this.makerDtStamp = makerDtStamp;
	}

	public Date getCheckerDtStamp() {
		return checkerDtStamp;
	}

	public void setCheckerDtStamp(Date checkerDtStamp) {
		this.checkerDtStamp = checkerDtStamp;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getFristTimeAuth() {
		return fristTimeAuth;
	}

	public void setFristTimeAuth(String fristTimeAuth) {
		this.fristTimeAuth = fristTimeAuth;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

}
