//package com.rumango.median.iso.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.rumango.median.dao.AuditLogRepository;
//import com.rumango.median.entity.AuditLog;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/auditlog")
//public class AuditLogController {
//
//	@Autowired
//	private AuditLogRepository auditLogRepository;
//
//	// /auditlog/getlog/0/10
//	@GetMapping("/getlog/{from}/{to}")
//	public List<AuditLog> getLogById(@PathVariable("from") int from, @PathVariable("to") int to) {
//		return auditLogRepository.findById(PageRequest.of(1, 10, Sort.by("externalSystemName")));
//	}
//}
