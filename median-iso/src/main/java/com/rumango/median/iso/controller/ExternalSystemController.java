package com.rumango.median.iso.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.median.dao.ExternalSystemRepository;
import com.rumango.median.entity.ExternalSystem;
import com.rumango.median.iso.dto.ExternalSystemDto;

@RestController
@CrossOrigin("*")
@RequestMapping("/extsys")
public class ExternalSystemController {
	private final static Logger logger = Logger.getLogger(ExternalSystemController.class);

	@Autowired
	private ExternalSystemRepository repo;

// /extsys/findAll
	@GetMapping("/findAll")
	public List<ExternalSystemDto> getAllExtsysNames() {
		logger.info("Inside getAllExtsysNames");
		ExternalSystemDto esDto;
		List<ExternalSystemDto> extSysNames = new LinkedList<>();
		for (ExternalSystem externalSystem : repo.findAll()) {
			esDto = new ExternalSystemDto();
			esDto.setExtSys(externalSystem.getExtSysName());
			extSysNames.add(esDto);
		}
		return extSysNames;
	}

	// /extsys/findProcess/{extsysName}
	@GetMapping("/findProcess/{extSys}")
	public List<ExternalSystemDto> getAllProcessNames(@PathVariable("extSys") String extSys) {
		logger.info("Inside getAllProcessNames based on external system name");
		ExternalSystemDto esDto = new ExternalSystemDto();
		List<ExternalSystemDto> extSysNames = new LinkedList<>();
		for (String externalSystem : repo.findProcessByExtSysName(extSys)) {
			esDto.setExtSys(extSys);
			esDto.setProcessName(externalSystem);
			extSysNames.add(esDto);
		}
		return extSysNames;
	}

	@GetMapping("/{destination}")
	public ExternalSystem getExtSys(@PathVariable("destination") String destination) {
		logger.info("Inside getresponse");
		return repo.findByDestination(destination);
	}

	@GetMapping("/getid/{systemCode}")
	public String getresponse(@PathVariable("systemCode") String systemCode) {
		logger.info("Inside getresponse");

		return repo.findIdBySysCode(systemCode).toString();
	}

	@GetMapping("/getid1/{systemCode}")
	// @Produces(MediaType.APPLICATION_JSON)
	public String getresponse1(@PathVariable("systemCode") String systemCode) {
		return repo.getStringId(systemCode);
	}

}
