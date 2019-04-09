package com.rumango.median.role;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends CrudRepository<RolesEntity, String> {
	RolesEntity findByRoleName(String roleName);

	@Transactional
	void deleteByRoleName(String roleName);

	@Query("from RolesEntity where authStatus = 'A'")
	public List<RolesEntity> fetchAuthRoles();

}
