package com.rumango.median.iso.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.median.iso.dao.ExternalSystemRepository;
import com.rumango.median.iso.entity.ExternalSystem;

@RestController
@CrossOrigin("*")
@RequestMapping("/iso/ext")
public class ExternalSystemController {
	private final static Logger logger = Logger.getLogger(ExternalSystemController.class);

	@Autowired
	private ExternalSystemRepository repo;

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
