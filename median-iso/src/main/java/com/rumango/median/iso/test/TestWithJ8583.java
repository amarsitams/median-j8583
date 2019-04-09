package com.rumango.median.iso.test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import com.rumango.median.iso.util.impl.IsoUtilImpl;

public class TestWithJ8583 {// implements DisposableBean implements InitializingBean

	private int maxResponseWaitingTime = 40000;
	private boolean isAsciiHeader = true;
	private String host = "192.168.0.34"; // "192.168.1.2";
	private int port = 10090;
	private String message;
	private Socket socket;

	// private final static Logger logger =
	// Logger.getLogger(ClientSocketForSwitch.class);

	public static void main(String[] args) throws Exception {
//		String iso93 = "1200FA3A800108E080000000000004000000061234560000110000000123450000000043111102155116000001181102155116181118110211020812312312232         123     2132           0533122003169876543210123456";
//		String iso94 = "1200FA3A800108E080000000000004000000061234560000110000000123450000000043111102155116000001181102155116181118110211020812312312232			 123 2132 0533122003169876543210123456";
//		String iso95 = "1200F030810100008000000000000400003804INTB3100000000000000000100622279      2019010212243520190102200060000008401500119000100027503SWT003ITB017PRN 2020540404332";
//		new TestWithJ8583().run(iso94);
		
		IsoUtilImpl util = new IsoUtilImpl();
		new TestWithJ8583().run(util.packMessage(buildIso(), "93"));
	}

	public void setValues(int waitingTime, boolean header, String host, int port) {
		this.maxResponseWaitingTime = waitingTime;
		this.isAsciiHeader = header;
		this.host = host;
		this.port = port;
	}

	public String run(String string) {
		try {
			System.out.println("inside run of client IsoSocket");
			InetAddress address = InetAddress.getByName(host);
			System.out.println("waitingTime ::: address ::  port " + "  ::: " + maxResponseWaitingTime + "  ::: "
					+ address + "  ::: " + port);
			socket = new Socket(address, port);
			System.out.println(socket.toString());
			// Send the message to the server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			message = string.trim();
			int len = message.length();
			if (len > 0) {
				String sendMessage = getTcpHeader(len) + message;
				bw.write(sendMessage);
				bw.flush();
				System.out.println("Message sent to the server : " + message);
				// Get the return message from the server
				InputStream is = socket.getInputStream();
				int msgLength = 0;
				byte[] responseMsg = null;
				byte[] b = new byte[4];
				for (int i = 0; (i < maxResponseWaitingTime) && (is.available() <= 0); i += 1) {
					// System.out.print(".");
					Thread.sleep(1L);
				}
				if (is.available() <= 0) {
					System.out.println("Response Not Availiable with in the " + maxResponseWaitingTime + " time");
					return "";
				}
				if (isAsciiHeader) {
					msgLength = is.read(b);
					msgLength = Integer.parseInt(new String(b, 0, msgLength));
				} else {
					for (int i = 0; i < 2; i++) {
						int ret;
						if ((ret = is.read()) != -1) {
							msgLength = msgLength << i * 8 | ret;
						}
					}
				}
				System.out.println("Received Message Length is:" + msgLength);
				responseMsg = new byte[msgLength];
				is.read(responseMsg, 0, msgLength);
				String recievedMessage = new String(responseMsg);
				System.out.println("Received Message is:" + recievedMessage);
				return recievedMessage;
			}
		} catch (Exception e) {
			System.out.println("exception in run of ClientSocketForSwitch" + e);
			return null;
		}
//		finally {
//			close();
//		}
		return "";
	}

	private String getTcpHeader(int length) {
		String tcpHeader = "";
		if (isAsciiHeader) {
			tcpHeader = (length < 9 ? "000" : length < 99 ? "00" : length < 999 ? "0" : "") + length;
			System.out.println("tcpHeader :" + tcpHeader);
			return tcpHeader;
		} else {
			tcpHeader = (char) (length - (length / 256) * 256) + tcpHeader;
			length = length / 256;
			tcpHeader = (char) (length) + tcpHeader;
			System.out.println("tcpHeader :" + tcpHeader);
			return tcpHeader;
		}
	}

	private static Map<Integer, String> buildIso() {
		Map<Integer, String> m = new LinkedHashMap<>();
		try {
			Calendar cal = Calendar.getInstance();
			String MM = "" + (1 + cal.get(Calendar.MONTH));
			String DD = "" + cal.get(Calendar.DAY_OF_MONTH);
			String hh = "" + cal.get(Calendar.HOUR_OF_DAY);
			String mm = "" + cal.get(Calendar.MINUTE);
			String ss = "" + cal.get(Calendar.SECOND);
			String YY = "" + cal.get(Calendar.YEAR);
			if (MM.length() < 2) {
				MM = "0" + MM;
			}
			if (DD.length() < 2) {
				DD = "0" + DD;
			}
			if (hh.length() < 2) {
				hh = "0" + hh;
			}
			if (mm.length() < 2) {
				mm = "0" + mm;
			}
			if (ss.length() < 2) {
				ss = "0" + ss;
			}
			if (YY.length() < 2) {
				YY = "0" + YY;
			}
			m.put(0, "1200");// 0 Is for MTI
			m.put(2, "INTB");
			m.put(3, "820000");
//			Balance inquiry    31
//			Cash withdrawal    01
//			Funds transfer     40
//			Account Inquiry    82			
			m.put(4, new DecimalFormat("0000000000000000").format(10));
			m.put(11, "418513");
			// m.put(12, YY + MM + DD + hh + mm + ss);
			m.put(12, "20161208092202");
			// m.put(17, YY + MM + DD);
			m.put(17, "20161208");
			m.put(24, "200");
			m.put(32, "000000");
			m.put(49, "840");
			// m.put(62,"00000000101");
			m.put(102, "001201010100045");
			m.put(103, "002201010100081");
			m.put(123, "SWT");
			m.put(124, "ITB");
			// m.put(125, "ABC0000019");

//			0 :1200
//			2 :008108021004255
//			3 :820000
//			4 :0000000000003939
//			11 :000000046960
//			12 :20161208013556
//			17 :20161218
//			24 :200
//			32 :000000
//			49 :840
//			102 :0090000001003
//			103 :001190001000309
//			123 :SWT
//			124 :ITB
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return m;
	}
}