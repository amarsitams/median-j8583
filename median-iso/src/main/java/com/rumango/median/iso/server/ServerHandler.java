package com.rumango.median.iso.server;

import java.net.InetSocketAddress;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.rumango.median.iso.dto.IsoDetailsDto;
import com.rumango.median.iso.service.GetResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter implements Callable<String> {

	private final static Logger logger = Logger.getLogger(ServerHandler.class);
	private GetResponse getResponse;
	String response = "";
	private String uuid, stringMessage;
	private IsoDetailsDto dto = new IsoDetailsDto();

	public ServerHandler(GetResponse response) {
		logger.info("Constructor of ServerHandler, GetResponse object: " + response);
		this.getResponse = response;
	}

//	public ServerHandler(CallableResponse response) {
//		this.callResponse = response;
//	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("inside channelRead of server handler : ");
		dto.setCreatedAt(new Timestamp(System.currentTimeMillis()));

		try {
			String fromIP = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress();
			dto.setFromIp(fromIP);
			logger.info("fromIp " + fromIP);
			StringIsoMessage isoMessage = (StringIsoMessage) msg;
			uuid = UUID.randomUUID().toString();
			dto.setUuid(uuid);
			logger.info("Incoming iso msg: " + isoMessage.getStr());

			try {
				this.stringMessage = isoMessage.getStr().substring(4);
//				callResponse.setValues(isoMessage.getStr().substring(4), dto);
//				response = callResponse.call();
				// response = getResponse.convertAndRespond(isoMessage.getStr().substring(4),
				// dto);

				response = call();
				if (response == null || response.length() < 2)
					response = "00";
			} catch (Exception e) {
				logger.error("Exception in getting response", e);
				response = "00";
			}

			logger.info("response iso msg: " + response);
			String sendMessage = getTcpHeader(response.length()) + response;
			logger.info("Response :" + sendMessage);
			isoMessage.setStr(sendMessage);
			ctx.write(isoMessage);
			ctx.flush();
		} catch (Exception e) {
			logger.error("Exception in channel read", e);
		}
	}

	private String getTcpHeader(int length) {
		String tcpHeader = "";
		tcpHeader = (length < 9 ? "000" : length < 99 ? "00" : length < 999 ? "0" : "") + length;
		logger.info("tcpHeader :" + tcpHeader);
		return tcpHeader;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.error("Exception in ServerHandler", cause);
		ctx.close();
	}

	@Override
	public String call() throws Exception {
		try {
			response = getResponse.convertAndRespond(this.stringMessage, this.dto);
			if (response == null || response.length() < 2)
				response = "00";
		} catch (Exception e) {
			logger.error("Exception in getting response", e);
			response = "00";
		}
		return response;
	}
}