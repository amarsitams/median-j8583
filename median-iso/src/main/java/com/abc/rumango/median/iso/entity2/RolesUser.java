package com.abc.rumango.median.iso.entity2;
//package com.rumango.median.iso.entity2;
//
//import java.io.Serializable;
//import javax.persistence.*;
//
//
///**
// * The persistent class for the roles_users database table.
// * 
// */
//@Entity
//@Table(name="roles_users")
//@NamedQuery(name="RolesUser.findAll", query="SELECT r FROM RolesUser r")
//public class RolesUser implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Column(name="role_id", nullable=false)
//	private Long roleId;
//
//	@Column(name="user_id", nullable=false)
//	private Long userId;
//
//	public RolesUser() {
//	}
//
//	public Long getRoleId() {
//		return this.roleId;
//	}
//
//	public void setRoleId(Long roleId) {
//		this.roleId = roleId;
//	}
//
//	public Long getUserId() {
//		return this.userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//}