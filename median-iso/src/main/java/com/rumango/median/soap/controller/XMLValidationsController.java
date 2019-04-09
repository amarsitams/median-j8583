/**
 * 
 */
package com.rumango.median.soap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.median.soap.service.XMLValidationsService;

/**
 * @author lei2o
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/xmlvalidationscontroller")
public class XMLValidationsController
{

	@Autowired
	private XMLValidationsService xmlvalidationsService;

	@GetMapping("/all/{from_sys}/{to_sys}")
	public void getAllValidations(@PathVariable("from_sys") String from_sys, @PathVariable("to_sys") String to_sys)
	{
		xmlvalidationsService.doAllMandatoryTagsAddition(from_sys, to_sys);
	}
}
