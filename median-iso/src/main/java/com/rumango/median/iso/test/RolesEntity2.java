package com.rumango.median.iso.test;
//package com.rumango.median.iso.entity;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "role2")
//public class RolesEntity2 {
//
//	private Long roleId;
//
//	private String roleName;
//
//	private Set<User2> users = new HashSet<>();
//
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//	public Set<User2> getUsers() {
//		return this.users;
//	}
//
//	public void setUsers(Set<User2> users) {
//		this.users = users;
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "role_id", unique = true, nullable = false)
//	public Long getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Long roleId) {
//		this.roleId = roleId;
//	}
//
//	@Column
//	public String getRoleName() {
//		return roleName;
//	}
//
//	public void setRoleName(String roleName) {
//		this.roleName = roleName;
//	}
//
//}
