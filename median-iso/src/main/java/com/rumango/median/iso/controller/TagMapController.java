package com.rumango.median.iso.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.median.iso.dao.TagMapRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/iso/tag")
public class TagMapController {
	private final static Logger logger = Logger.getLogger(TagMapController.class);

	@Autowired
	TagMapRepository repo;

	@GetMapping("/getid/{from}/{to}")
	// @Produces(MediaType.APPLICATION_JSON)
	public String getresponse(@PathVariable("from") Long from, @PathVariable("to") Long to) {
		logger.info("Inside getresponse");
		return repo.getId(from, to).toString();
	}

	@GetMapping("/getid1/{from}/{to}")
	// @Produces(MediaType.APPLICATION_JSON)
	public String getresponse1(@PathVariable("from") Long from, @PathVariable("to") Long to) {
		return repo.findByFromAndTo(from, to) + "";
	}
}
