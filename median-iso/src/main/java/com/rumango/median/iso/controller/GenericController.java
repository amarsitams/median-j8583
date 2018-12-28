package com.rumango.median.iso.controller;
//package com.rumango.median.iso.controller;
//
//import java.util.List;
//
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.rumango.median.iso.dao.IsoLovRepository;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/iso")
//public class GenericController {
//	@Autowired
//	private IsoLovRepository genericRepo;
//
//	@GetMapping("/getlovrules")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Object[]> getAllRules() {
//		return genericRepo.getStringValues();
//	}
//
////	@GetMapping("/getlovvalues/{fieldNumber}")
////	@Produces(MediaType.APPLICATION_JSON)
////	public List<String> getLovValues(@PathVariable("fieldNumber") Integer fieldNumber) {
////		return genericRepo.getAllRules(fieldNumber);
////	}
//}
