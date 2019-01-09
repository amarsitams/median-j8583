package com.rumango.median.iso.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=2147483647)
	private String applications;

	@Column(name="change_password")
	private Boolean changePassword;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="creator_dt_stamp")
	private Timestamp creatorDtStamp;

	@Column(name="creator_id")
	private Integer creatorId;

	@Column(name="current_sign_in_at")
	private Timestamp currentSignInAt;

	@Column(name="current_sign_in_ip")
	private String currentSignInIp;

	@Column(nullable=false)
	private String email;

	@Column(name="email_notification")
	private Boolean emailNotification;

	@Column(name="encrypted_password", nullable=false)
	private String encryptedPassword;

	@Column(name="failed_attempts", nullable=false)
	private Integer failedAttempts;

	@Column(name="last_sign_in_at")
	private Timestamp lastSignInAt;

	@Column(name="last_sign_in_ip")
	private String lastSignInIp;

	@Column(name="ldap_user_id", length=35)
	private String ldapUserId;

	@Column(name="locked_at")
	private Timestamp lockedAt;

	@Column(length=35)
	private String mobile;

	@Column(name="msad_user_id", length=35)
	private String msadUserId;

	@Column(name="notification_emails")
	private String notificationEmails;

	@Column(name="notification_mobiles")
	private String notificationMobiles;

	@Column(name="record_status", length=35)
	private String recordStatus;

	@Column(name="remember_created_at")
	private Timestamp rememberCreatedAt;

	@Column(name="reset_password_sent_at")
	private Timestamp resetPasswordSentAt;

	@Column(name="reset_password_token")
	private String resetPasswordToken;

	@Column(name="sign_in_count", nullable=false)
	private Integer signInCount;

	@Column(name="sms_notification")
	private Boolean smsNotification;

	@Column(name="unlock_token")
	private String unlockToken;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	@Column(name="user_id")
	private String userId;

	@Column(name="user_name", length=35)
	private String userName;

	private Boolean verified;

	@Column(name="verifier_dt_stamp")
	private Timestamp verifierDtStamp;

	@Column(name="verifier_id")
	private Integer verifierId;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplications() {
		return this.applications;
	}

	public void setApplications(String applications) {
		this.applications = applications;
	}

	public Boolean getChangePassword() {
		return this.changePassword;
	}

	public void setChangePassword(Boolean changePassword) {
		this.changePassword = changePassword;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getCreatorDtStamp() {
		return this.creatorDtStamp;
	}

	public void setCreatorDtStamp(Timestamp creatorDtStamp) {
		this.creatorDtStamp = creatorDtStamp;
	}

	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Timestamp getCurrentSignInAt() {
		return this.currentSignInAt;
	}

	public void setCurrentSignInAt(Timestamp currentSignInAt) {
		this.currentSignInAt = currentSignInAt;
	}

	public String getCurrentSignInIp() {
		return this.currentSignInIp;
	}

	public void setCurrentSignInIp(String currentSignInIp) {
		this.currentSignInIp = currentSignInIp;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEmailNotification() {
		return this.emailNotification;
	}

	public void setEmailNotification(Boolean emailNotification) {
		this.emailNotification = emailNotification;
	}

	public String getEncryptedPassword() {
		return this.encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Integer getFailedAttempts() {
		return this.failedAttempts;
	}

	public void setFailedAttempts(Integer failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public Timestamp getLastSignInAt() {
		return this.lastSignInAt;
	}

	public void setLastSignInAt(Timestamp lastSignInAt) {
		this.lastSignInAt = lastSignInAt;
	}

	public String getLastSignInIp() {
		return this.lastSignInIp;
	}

	public void setLastSignInIp(String lastSignInIp) {
		this.lastSignInIp = lastSignInIp;
	}

	public String getLdapUserId() {
		return this.ldapUserId;
	}

	public void setLdapUserId(String ldapUserId) {
		this.ldapUserId = ldapUserId;
	}

	public Timestamp getLockedAt() {
		return this.lockedAt;
	}

	public void setLockedAt(Timestamp lockedAt) {
		this.lockedAt = lockedAt;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsadUserId() {
		return this.msadUserId;
	}

	public void setMsadUserId(String msadUserId) {
		this.msadUserId = msadUserId;
	}

	public String getNotificationEmails() {
		return this.notificationEmails;
	}

	public void setNotificationEmails(String notificationEmails) {
		this.notificationEmails = notificationEmails;
	}

	public String getNotificationMobiles() {
		return this.notificationMobiles;
	}

	public void setNotificationMobiles(String notificationMobiles) {
		this.notificationMobiles = notificationMobiles;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Timestamp getRememberCreatedAt() {
		return this.rememberCreatedAt;
	}

	public void setRememberCreatedAt(Timestamp rememberCreatedAt) {
		this.rememberCreatedAt = rememberCreatedAt;
	}

	public Timestamp getResetPasswordSentAt() {
		return this.resetPasswordSentAt;
	}

	public void setResetPasswordSentAt(Timestamp resetPasswordSentAt) {
		this.resetPasswordSentAt = resetPasswordSentAt;
	}

	public String getResetPasswordToken() {
		return this.resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public Integer getSignInCount() {
		return this.signInCount;
	}

	public void setSignInCount(Integer signInCount) {
		this.signInCount = signInCount;
	}

	public Boolean getSmsNotification() {
		return this.smsNotification;
	}

	public void setSmsNotification(Boolean smsNotification) {
		this.smsNotification = smsNotification;
	}

	public String getUnlockToken() {
		return this.unlockToken;
	}

	public void setUnlockToken(String unlockToken) {
		this.unlockToken = unlockToken;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getVerified() {
		return this.verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Timestamp getVerifierDtStamp() {
		return this.verifierDtStamp;
	}

	public void setVerifierDtStamp(Timestamp verifierDtStamp) {
		this.verifierDtStamp = verifierDtStamp;
	}

	public Integer getVerifierId() {
		return this.verifierId;
	}

	public void setVerifierId(Integer verifierId) {
		this.verifierId = verifierId;
	}

}