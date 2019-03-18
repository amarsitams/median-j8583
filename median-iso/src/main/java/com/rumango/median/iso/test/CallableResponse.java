package com.rumango.median.iso.test;
//package com.rumango.median.iso.serviceimpl;
//
//import java.util.concurrent.Callable;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.rumango.median.iso.dto.IsoDetailsDto;
//import com.rumango.median.iso.service.GetResponse;
//
//@Component
//public class CallableResponse implements Callable<String> {
//
//	@Autowired
//	private GetResponse getResponse;
//
//	private String stringMessage;
//	private IsoDetailsDto dto;
//
//	// private final static Logger logger =
//	// Logger.getLogger(CallableResponse.class);
//
//	public void setValues(String stringMessage, IsoDetailsDto dto) {
//		this.stringMessage = stringMessage;
//		this.dto = dto;
//	}
//
//	@Override
//	public String call() throws Exception {
//		return getResponse.convertAndRespond(this.stringMessage, this.dto);
//	}
//}
