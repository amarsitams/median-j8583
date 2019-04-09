/**
 * 
 */
package com.rumango.median.soap.producer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lei2o
 *
 */
import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ofss.fcubs.service.fcubsftservice.QUERYCONTRACTIOFSREQRequest;
import com.ofss.fcubs.service.fcubsftservice.QUERYCONTRACTIOFSRESResponse;

@Component
public class QueryContractRepository
{
	private static final Map<QUERYCONTRACTIOFSREQRequest, QUERYCONTRACTIOFSRESResponse> requestAndResponseMap = new HashMap<QUERYCONTRACTIOFSREQRequest, QUERYCONTRACTIOFSRESResponse>();

	private static final Logger LOGGER = Logger.getLogger(QueryContractRepository.class);

	@PostConstruct
	public void initData()
	{
		LOGGER.info("=============>QueryContractRepository,initData() <======");
	}

	public QUERYCONTRACTIOFSRESResponse findResponse(QUERYCONTRACTIOFSREQRequest request)
	{
		LOGGER.info("=============>QueryContractRepository,findResponse(QUERYCONTRACTIOFSREQRequest request) <======");
		Assert.notNull(request, "The request must not be null");
		return requestAndResponseMap.get(request);
	}
}
