package com.rumango.median.iso.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.median.iso.dao.NodeMapRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/iso/node")
public class NodeMapController {
	private final static Logger logger = Logger.getLogger(NodeMapController.class);

	@Autowired
	NodeMapRepository repo;

	@GetMapping("/getid/{id}")
	public String getresponse(@PathVariable("id") Long id) {
		logger.info("Inside getresponse");
		return repo.getId(id).toString();
	}

	@GetMapping("/getid1/{id}")
	public String getresponse1(@PathVariable("id") Long id) {
		return repo.findByTagMapId2(id) + "";
	}

	@GetMapping("/getid/{from}/{to}")
	public String getresponse(@PathVariable("from") String from, @PathVariable("to") String to) {
		return repo.getQuery(from, to).toString();
	}
}
