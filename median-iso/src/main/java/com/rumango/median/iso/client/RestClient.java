package com.rumango.median.iso.client;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.rumango.median.iso.dto.ValidateChannel;

public class RestClient {
	private static final String urlPath = "http://172.16.2.178:8080/ValidateAPI/rest/service/check?channelID=";
	private final static Logger logger = Logger.getLogger(RestClient.class);

	public static ValidateChannel callRestApi(String channelId, String transactionId) {
		logger.info("RestClient called with channelId :" + channelId + " transactionId :" + transactionId);
		RestTemplate restTemplate = new RestTemplate();
		String url = urlPath + channelId + "&transactionID=" + transactionId;
		ValidateChannel response = restTemplate.getForObject(url, ValidateChannel.class);
		return response;
	}
}
