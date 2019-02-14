//package com.rumango.median.iso.dao;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import com.rumango.median.iso.entity.RolesEntity2;
//
//@Repository
//public interface RolesRepo extends CrudRepository<RolesEntity2, String> {
//	RolesEntity2 findByRoleName(String roleName);
//	
//	@Query(value="SELECT t FROM RolesEntity t WHERE roleName=:roleName")
//	RolesEntity2 findCustom(String roleName);
//	
//
//	default RolesEntity2 getOne(String roleName) {
//		return findByRoleName(roleName);
//	}
//
//}
