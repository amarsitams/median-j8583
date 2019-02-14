package com.rumango.median.iso.test;
//package com.rumango.median.iso.entity;
//
//import java.io.Serializable;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
///**
// * The persistent class for the users database table.
// * 
// */
//@Entity
//@Table(name = "users2")
//public class User2 implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	private Long userId;
//
//	private String applications;
//	private Set<RolesEntity2> roles = new HashSet<>();
//
//	public void setRoles(Set<RolesEntity2> roles) {
//		this.roles = roles;
//	}
//
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "user_roles", joinColumns = {
//			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
//					@JoinColumn(name = "role_id", nullable = false, updatable = false) })
//	public Set<RolesEntity2> getRoles() {
//		return roles;
//	}
//
//	@Column
//	public String getApplications() {
//		return applications;
//	}
//
//	public void setApplications(String applications) {
//		this.applications = applications;
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "user_id", unique = true, nullable = false)
//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//}