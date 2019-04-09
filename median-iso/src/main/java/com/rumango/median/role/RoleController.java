package com.rumango.median.role;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

	RolesRepo rolesRepo;

	@GetMapping("/roles")
	public List<RolesEntity> getAllRoless() {
		System.out.println("Get all Roles...");
		return (List<RolesEntity>) rolesRepo.findAll();
	}
}
