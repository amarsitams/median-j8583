package com.rumango.median.iso.server;

import java.net.InetSocketAddress;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.rumango.median.iso.service.GetResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	private final static Logger logger = Logger.getLogger(ServerHandler.class);
	private GetResponse getResponse;
	private String uuid;
	private Map<String, String> map = new LinkedHashMap<>();

	public ServerHandler(GetResponse response) {
		this.getResponse = response;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		map.put("createdAt", new Timestamp(System.currentTimeMillis()).toString());
		logger.info("inside channelRead of server handler : ");
		String response;
		try {
			String strIP = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress();
			map.put("IP", strIP);
			logger.info("IP " + strIP);
			StringIsoMessage isoMessage = (StringIsoMessage) msg;
			uuid = UUID.randomUUID().toString();
			map.put("uuid", uuid);
			logger.info("Incoming iso msg: " + isoMessage.getStr());
			// response = getResponse.convertAndRespond(test(), map);
			// FileWriter f = new FileWriter("E:\\output.txt", true);
			// f.write(isoMessage.getStr() + "\n");
			// f.append("\n next line");
			// f.write("Unpacked iso8583 Message" + PARSEDISOMESSAGE);
			// f.close();

			response = getResponse.convertAndRespond(isoMessage.getStr().substring(4), map);

			// response = getResponse.convertAndRespond(isoMessage.getStr());
			logger.info("response iso msg: " + response);
			String sendMessage = getTcpHeader(response.length()) + response;
			logger.info("Response :" + sendMessage);
			isoMessage.setStr(sendMessage);
			ctx.write(isoMessage);
			ctx.flush();
		} catch (Exception e) {
			e.printStackTrace();
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
		logger.error("Exception in ServerHandler");
		ctx.close();
	}
}