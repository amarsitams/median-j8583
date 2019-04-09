package com.rumango.median.iso.serviceimpl;

import java.time.Year;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.entity.NodeMap;
import com.rumango.median.iso.constants.IsoConstants;
import com.rumango.median.iso.dao.service.ValidationsService;
import com.rumango.median.iso.service.ConvertIso;

@Service
public class ConvertIsoImpl implements ConvertIso {

	private final static Logger logger = Logger.getLogger(ConvertIsoImpl.class);

	@Autowired
	private ValidationsService validationsService;

	public Map<Integer, String> convertIso(String sourceIsoVersion, String destIsoVersion,
			Map<Integer, String> isoMsg) {
		if (sourceIsoVersion.equalsIgnoreCase(IsoConstants.version_87)
				&& destIsoVersion.equalsIgnoreCase(IsoConstants.version_87)) {
			// return testValidations(isoMsg);
			return isoMsg;
		}
		if (sourceIsoVersion.equalsIgnoreCase(IsoConstants.version_93)
				&& destIsoVersion.equalsIgnoreCase(IsoConstants.version_93)) {
			return isoMsg;
		}
		if (sourceIsoVersion.equalsIgnoreCase(IsoConstants.version_ge)
				&& destIsoVersion.equalsIgnoreCase(IsoConstants.version_ge)) {
			return isoMsg;
		}
		if (sourceIsoVersion.equalsIgnoreCase(IsoConstants.version_87)
				&& destIsoVersion.equalsIgnoreCase(IsoConstants.version_93)) {
			return iso87TO93(isoMsg);
		}
		if (sourceIsoVersion.equalsIgnoreCase(IsoConstants.version_93)
				&& destIsoVersion.equalsIgnoreCase(IsoConstants.version_87)) {
			return iso93To87(isoMsg);

		}
		if (sourceIsoVersion.equalsIgnoreCase(IsoConstants.version_87)
				&& destIsoVersion.equalsIgnoreCase(IsoConstants.version_ge)) {
			return iso87ToGeneric(isoMsg);

		}
		if (sourceIsoVersion.equalsIgnoreCase(IsoConstants.version_ge)
				&& destIsoVersion.equalsIgnoreCase(IsoConstants.version_87)) {
			return genericTo87(isoMsg);

		}
		if (sourceIsoVersion.equalsIgnoreCase(IsoConstants.version_93)
				&& destIsoVersion.equalsIgnoreCase(IsoConstants.version_ge)) {
			return iso93ToGeneric(isoMsg);

		}
		if (sourceIsoVersion.equalsIgnoreCase(IsoConstants.version_ge)
				&& destIsoVersion.equalsIgnoreCase(IsoConstants.version_93)) {
			return genericTo93(isoMsg);
		} else
			return null;
	}

	public Map<Integer, String> testValidations(Map<Integer, String> isoMsg) {
//		Map<Integer, String> tempMap = new LinkedHashMap<>();
//		String value = null;
		Map<Integer, NodeMap> validations = validationsService.getAllValidations("192.168.1.10");
		for (Map.Entry<Integer, String> entry : isoMsg.entrySet()) {
			if (validations.containsKey(entry.getKey())) {
				logger.warn("Validations available ::" + entry.getKey() + " :: "
						+ validations.get(entry.getKey()).toString());
			}
//			tempMap.put(entry.getKey(), value == null ? entry.getValue() : value);
//			logger.info(entry.getKey() + " " + ":" + value);
		}
		return isoMsg;
	}

	private Map<Integer, String> iso87ToGeneric(Map<Integer, String> isoMsg) {
		logger.info("inside iso87ToGeneric");
		Map<Integer, String> isoMessage = isoMsg;
		Map<Integer, String> temp = new LinkedHashMap<>(isoMsg);
		for (Map.Entry<Integer, String> entry : temp.entrySet()) {
			switch (entry.getKey()) {
			case 1:
				StringBuilder sb = new StringBuilder(entry.getValue());
				sb.setCharAt(0, '1');
				isoMessage.put(1, sb.toString());
				break;
			case 2:
				isoMessage.put(2, entry.getValue());
				break;
			case 3:
				isoMessage.put(3, entry.getValue());
				break;
			case 4:
				isoMessage.put(4, entry.getValue());
				break;
			case 5:
				isoMessage.put(5, entry.getValue());
				break;
			case 7:
				isoMessage.put(7, entry.getValue());
				break;
			case 11:
				isoMessage.put(11, entry.getValue());
				break;
			case 12:
				isoMessage.put(12, entry.getValue());
				break;
			case 13:
				isoMessage.put(13, entry.getValue());
				break;
			case 15:
				isoMessage.put(15, entry.getValue());
				break;
			case 17:
				isoMessage.put(17, entry.getValue());
				break;
			case 32:
				isoMessage.put(32, entry.getValue());
				break;
			case 37:
				isoMessage.put(37, entry.getValue());
				break;
			case 41:
				isoMessage.put(41, entry.getValue());
				break;
			case 42:
				isoMessage.put(42, entry.getValue());
				break;
			case 43:
				isoMessage.put(43, entry.getValue());
				break;
			case 49:
				isoMessage.put(49, entry.getValue());
				break;
			case 102:
				isoMessage.put(102, entry.getValue());
				break;
			}
		}
		return isoMessage;
	}

	private Map<Integer, String> genericTo87(Map<Integer, String> isoMsg) {
		logger.info("inside genericTo87");
		Map<Integer, String> isoMessage = isoMsg;
		Map<Integer, String> temp = new LinkedHashMap<>(isoMsg);
		int len;
		StringBuilder builderTemp;
		String strTemp;
		for (Map.Entry<Integer, String> entry : temp.entrySet()) {
			switch (entry.getKey()) {
			// Replace MTI
			case 1:
				strTemp = entry.getValue();
				builderTemp = new StringBuilder(strTemp);
				builderTemp.setCharAt(0, '0');
				isoMessage.put(1, builderTemp.toString());
				break;
			case 4:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 12)
					isoMessage.put(4, strTemp.substring(len - 12));
				else
					isoMessage.put(4, strTemp);
				break;
			case 5:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 12)
					isoMessage.put(5, strTemp.substring(len - 12));
				else
					isoMessage.put(5, strTemp);
				break;
			case 11:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 6)
					isoMessage.put(11, strTemp.substring(len - 6));
				else
					isoMessage.put(11, strTemp);
				break;
			case 12:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 6)
					isoMessage.put(12, strTemp.substring(len - 6));
				else
					isoMessage.put(12, strTemp);
				break;
			case 15:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 4)
					isoMessage.put(15, strTemp.substring(len - 4));
				else
					isoMessage.put(15, strTemp);
				break;
			case 16:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 4)
					isoMessage.put(16, strTemp.substring(len - 4));
				else
					isoMessage.put(16, strTemp);
				break;
			case 17:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 4)
					isoMessage.put(17, strTemp.substring(len - 4));
				else
					isoMessage.put(17, strTemp);
				break;
			case 22:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 3)
					isoMessage.put(22, strTemp.substring(len - 3));
				else
					isoMessage.put(22, strTemp);
				break;
			case 25:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 2)
					isoMessage.put(25, strTemp.substring(len - 2));
				else
					isoMessage.put(25, strTemp);
				break;
			case 26:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 2)
					isoMessage.put(26, strTemp.substring(len - 2));
				else
					isoMessage.put(26, strTemp);
				break;
//			case 28:
//				break;
//			case 29:
//				break;
			case 30:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 9)
					isoMessage.put(30, strTemp.substring(len - 9));
				else
					isoMessage.put(30, strTemp);
				break;
			case 31:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 9)
					isoMessage.put(31, strTemp.substring(len - 9));
				else
					isoMessage.put(31, strTemp);
				break;
			case 39:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 2)
					isoMessage.put(39, strTemp.substring(1));
				else
					isoMessage.put(39, strTemp);
				break;
			case 41:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 8)
					isoMessage.put(41, strTemp.substring(len - 8));
				else
					isoMessage.put(41, strTemp);
				break;
			case 43:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 40)
					isoMessage.put(43, strTemp.substring(len - 40));
				else
					isoMessage.put(43, strTemp);
				break;
//			case 46:
//				break;
//			case 56:
//				break;
//			case 60:
//				break;
//			case 62:
//				break;
//			case 63:
//				break;
			case 72:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 4)
					isoMessage.put(72, strTemp.substring(len - 4));
				else
					isoMessage.put(72, strTemp);
				break;
			case 93:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 6)
					isoMessage.put(93, strTemp.substring(len - 6));
				else
					isoMessage.put(93, strTemp);
				break;
			case 94:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 7)
					isoMessage.put(94, strTemp.substring(len - 7));
				else
					isoMessage.put(94, strTemp);
				break;
			case 95:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 42)
					isoMessage.put(95, strTemp.substring(len - 42));
				else
					isoMessage.put(95, strTemp);
				break;
			case 102:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 28)
					isoMessage.put(102, strTemp.substring(len - 28));
				else
					isoMessage.put(102, strTemp);
				break;
			case 103:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 28)
					isoMessage.put(103, strTemp.substring(len - 28));
				else
					isoMessage.put(103, strTemp);
				break;
//			case 123:
//				break;
			default:
				break;
			}
		}
		return isoMessage;
	}

	private Map<Integer, String> genericTo93(Map<Integer, String> isoMsg) {
		logger.info("inside genericTo93");
		Map<Integer, String> isoMessage = isoMsg;
		Map<Integer, String> temp = new LinkedHashMap<>(isoMsg);
		int len;
		String strTemp;
		for (Map.Entry<Integer, String> entry : temp.entrySet()) {
			switch (entry.getKey()) {
			case 4:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 12)
					isoMessage.put(4, strTemp.substring(len - 12));
				else
					isoMessage.put(4, strTemp);
				break;
			case 5:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 12)
					isoMessage.put(5, strTemp.substring(len - 12));
				else
					isoMessage.put(5, strTemp);
				break;
			case 11:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 6)
					isoMessage.put(11, strTemp.substring(len - 6));
				else
					isoMessage.put(11, strTemp);
				break;
			case 12:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 12)
					isoMessage.put(12, strTemp.substring(len - 12));
				else
					isoMessage.put(12, strTemp);
				break;
			case 15:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 6)
					isoMessage.put(15, strTemp.substring(len - 6));
				else
					isoMessage.put(15, strTemp);
				break;
			case 16:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 4)
					isoMessage.put(16, strTemp.substring(len - 4));
				else
					isoMessage.put(16, strTemp);
				break;
			case 17:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 4)
					isoMessage.put(17, strTemp.substring(len - 4));
				else
					isoMessage.put(17, strTemp);
				break;

			case 41:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 8)
					isoMessage.put(41, strTemp.substring(len - 8));
				else
					isoMessage.put(41, strTemp);
				break;
			case 46:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 204)
					isoMessage.put(46, strTemp.substring(len - 204));
				else
					isoMessage.put(46, strTemp);
				break;

			case 55:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 255)
					isoMessage.put(55, strTemp.substring(len - 255));
				else
					isoMessage.put(55, strTemp);
				break;
			case 57:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 3)
					isoMessage.put(57, strTemp.substring(len - 3));
				else
					isoMessage.put(57, strTemp);
				break;

			case 58:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 11)
					isoMessage.put(58, strTemp.substring(len - 11));
				else
					isoMessage.put(58, strTemp);
				break;

			case 82:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 10)
					isoMessage.put(82, strTemp.substring(len - 10));
				else
					isoMessage.put(82, strTemp);
				break;
			case 83:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 10)
					isoMessage.put(83, strTemp.substring(len - 10));
				else
					isoMessage.put(83, strTemp);
				break;

			case 84:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 10)
					isoMessage.put(84, strTemp.substring(len - 10));
				else
					isoMessage.put(84, strTemp);
				break;

			case 85:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 10)
					isoMessage.put(85, strTemp.substring(len - 10));
				else
					isoMessage.put(85, strTemp);
				break;

			case 90:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 10)
					isoMessage.put(90, strTemp.substring(len - 10));
				else
					isoMessage.put(90, strTemp);
				break;

			case 94:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 7)
					isoMessage.put(94, strTemp.substring(len - 7));
				else
					isoMessage.put(94, strTemp);
				break;
			case 95:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 42)
					isoMessage.put(95, strTemp.substring(len - 42));
				else
					isoMessage.put(95, strTemp);
				break;
			case 102:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 28)
					isoMessage.put(102, strTemp.substring(len - 28));
				else
					isoMessage.put(102, strTemp);
				break;
			case 105:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 16)
					isoMessage.put(105, strTemp.substring(len - 16));
				else
					isoMessage.put(105, strTemp);
				break;

			case 106:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 16)
					isoMessage.put(106, strTemp.substring(len - 16));
				else
					isoMessage.put(106, strTemp);
				break;

			case 107:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 10)
					isoMessage.put(107, strTemp.substring(len - 10));
				else
					isoMessage.put(107, strTemp);
				break;

			case 108:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 10)
					isoMessage.put(108, strTemp.substring(len - 10));
				else
					isoMessage.put(108, strTemp);
				break;

			case 109:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 84)
					isoMessage.put(109, strTemp.substring(len - 84));
				else
					isoMessage.put(109, strTemp);
				break;

			case 110:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 84)
					isoMessage.put(110, strTemp.substring(len - 84));
				else
					isoMessage.put(110, strTemp);
				break;

			default:
				break;
			}
		}
		return isoMessage;
	}

	private Map<Integer, String> iso93ToGeneric(Map<Integer, String> isoMsg) {
		logger.info("inside iso93ToGeneric");
		Map<Integer, String> isoMessage = isoMsg;
		Map<Integer, String> temp = new LinkedHashMap<>(isoMsg);
		int len;
		String strTemp;
		for (Map.Entry<Integer, String> entry : temp.entrySet()) {
			switch (entry.getKey()) {

			case 44:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 25)
					isoMessage.put(44, strTemp.substring(len - 25));
				else
					isoMessage.put(44, strTemp);
				break;

			case 53:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 16)
					isoMessage.put(53, strTemp.substring(len - 16));
				else
					isoMessage.put(53, strTemp);
				break;
			case 60:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 9)
					isoMessage.put(60, strTemp.substring(len - 9));
				else
					isoMessage.put(60, strTemp);
				break;

			case 62:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 19)
					isoMessage.put(62, strTemp.substring(len - 19));
				else
					isoMessage.put(62, strTemp);
				break;
			case 63:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 25)
					isoMessage.put(63, strTemp.substring(len - 25));
				else
					isoMessage.put(63, strTemp);
				break;

			case 65:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 1)
					isoMessage.put(65, strTemp.substring(len - 1));
				else
					isoMessage.put(65, strTemp);
				break;

			case 66:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 1)
					isoMessage.put(66, strTemp.substring(len - 1));
				else
					isoMessage.put(66, strTemp);
				break;

			case 71:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 4)
					isoMessage.put(71, strTemp.substring(len - 4));
				else
					isoMessage.put(71, strTemp);
				break;

			case 91:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 1)
					isoMessage.put(91, strTemp.substring(len - 1));
				else
					isoMessage.put(91, strTemp);
				break;

			case 92:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 2)
					isoMessage.put(92, strTemp.substring(len - 2));
				else
					isoMessage.put(92, strTemp);
				break;

			case 96:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 16)
					isoMessage.put(96, strTemp.substring(len - 16));
				else
					isoMessage.put(96, strTemp);
				break;

			case 123:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 3)
					isoMessage.put(123, strTemp.substring(len - 3));
				else
					isoMessage.put(123, strTemp);
				break;

			default:
				break;
			}
		}
		return isoMessage;
	}

	private Map<Integer, String> iso93To87(Map<Integer, String> isoMsg) {
		logger.info("inside iso93To87");
		Map<Integer, String> isoMessage = isoMsg;
		Map<Integer, String> temp = new LinkedHashMap<>(isoMsg);
		int len;
		StringBuilder builderTemp;
		String strTemp, iso12 = "";
		for (Map.Entry<Integer, String> entry : temp.entrySet()) {
			switch (entry.getKey()) {
			// Replace MTI
			case 1:
				strTemp = entry.getValue();
				builderTemp = new StringBuilder(strTemp);
				builderTemp.setCharAt(0, '0');
				isoMessage.put(0, builderTemp.toString());
				break;
			case 3:
				// isoMessage.put(i, String.format("%6s", validations.validate(i,
				// isoMessage.get(i))));
				break;
			// 012 new IFA_NUMERIC ( 6, "TIME, LOCAL TRANSACTION"),
			case 12:
				strTemp = entry.getValue();
				iso12 = strTemp;
				if (strTemp.length() > 6)
					isoMessage.put(12, strTemp.substring(6));
				else
					isoMessage.put(12, strTemp);
				break;
			// 013 new IFA_NUMERIC ( 4, "DATE, LOCAL TRANSACTION"),
			case 13:
				isoMessage.put(13, iso12.substring(2, 6));
				break;
			// 015 new IFA_NUMERIC ( 4, "DATE, SETTLEMENT"),
			case 15:
				strTemp = entry.getValue();
				isoMessage.put(15, strTemp.substring(2));
				break;
			// 022 new IFA_NUMERIC ( 3, "POINT OF SERVICE ENTRY MODE"),
			case 22:
				strTemp = entry.getValue();
				isoMessage.put(22, strTemp.substring(2));
				break;
			// 025 new IFA_NUMERIC ( 2, "POINT OF SERVICE CONDITION CODE")
			case 25:
				strTemp = entry.getValue();
				isoMessage.put(25, strTemp.substring(2));
				break;
			// 026 new IFA_NUMERIC ( 2, "POINT OF SERVICE PIN CAPTURE CODE")
			case 26:
				strTemp = entry.getValue();
				isoMessage.put(26, strTemp.substring(2));
				break;
			// 028 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION FEE")
			case 28:
				// isoMessage.put(i, String.format("%9s", validations.validate(i,
				// isoMessage.get(i))));
				//
				break;
			// 029 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT FEE")
			case 29:
				// isoMessage.put(i, String.format("%9s", validations.validate(i,
				// isoMessage.get(i))));
				//
				break;
			// 030 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION PROCESSING FEE")
			case 30:

				break;
			// 031 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT PROCESSING FEE")
			case 31:

				break;
			case 32:
				// isoMessage.put(i, String.format("%11s", validations.validate(i,
				// isoMessage.get(i))));
				break;
			// 039 new IF_CHAR ( 2, "RESPONSE CODE")
			case 39:
				strTemp = entry.getValue();
				isoMessage.put(39, strTemp.substring(1));
				break;
			// 043 new IF_CHAR ( 40, "CARD ACCEPTOR NAME/LOCATION")
			case 43:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 40) {
					isoMessage.put(43, strTemp.substring(0, 39));
				} else
					isoMessage.put(43, strTemp);
				break;
			// 044 new IFA_LLCHAR ( 25, "ADITIONAL RESPONSE DATA")
			case 44:
				strTemp = entry.getValue();
				len = strTemp.length();
				if (len > 25) {
					isoMessage.put(44, strTemp.substring(0, 24));
				} else
					isoMessage.put(44, strTemp);
				break;

			case 46:

//					strTemp = entry.getValue();
//					len = strTemp.length();
//					if (len > 25) {
//						isoMessage.put(44, strTemp.substring(0, 24));
//					} else
//						isoMessage.put(44, strTemp);
				break;

			case 49:
				// isoMessage.put(i, String.format("%3s", validations.validate(i,
				// isoMessage.get(i))));
				//
				break;
			case 53:

				break;
			case 55:

				break;

			case 56:
				strTemp = entry.getValue();
				isoMessage.put(90, strTemp.substring(1));
				break;

			case 57:

				break;

			case 58:

				break;

			case 65:

				break;

			case 66:

				break;

			// 071 new IFA_NUMERIC ( 4, "MESSAGE NUMBER")
			case 71:
				strTemp = entry.getValue();
				isoMessage.put(71, strTemp.substring(0, 4));
				break;

			case 72:

				break;

			case 82:

				break;

			case 83:

				break;

			case 84:

				break;

			case 85:

				break;

			case 90:

				break;

			case 91:

				break;

			case 92:
				strTemp = entry.getValue();
				isoMessage.put(19, strTemp);

				break;
			case 93:

				break;

			case 94:

				break;

			case 95:

				break;

			case 96:

				break;
			case 105:

				break;

			case 106:

				break;

			case 107:

				break;

			case 108:

				break;

			case 109:

				break;

			case 110:

				break;

			default:
				break;
			}
		}
		return isoMessage;
	}

	private Map<Integer, String> iso87TO93(Map<Integer, String> isoMsg) {
		logger.info("inside iso87TO93");
		Map<Integer, String> isoMessage = isoMsg;
		Map<Integer, String> temp = new LinkedHashMap<>(isoMsg);
		StringBuilder builderTemp;
		String strTemp, iso12 = "";
		int year = Year.now().getValue() % 100;
		for (Map.Entry<Integer, String> entry : temp.entrySet()) {
			switch (entry.getKey()) {
			// Replace MTI
			case 0:
				strTemp = entry.getValue();
				builderTemp = new StringBuilder(strTemp);
				builderTemp.setCharAt(0, '1');
				isoMessage.put(0, builderTemp.toString());
				break;
			// 012 new IFA_NUMERIC ( 12, "TIME, LOCAL TRANSACTION")
			case 12:
				builderTemp = new StringBuilder();
				builderTemp.append(year).append(isoMessage.get(13)).append(isoMessage.get(13));
				iso12 = builderTemp.toString();
				isoMessage.put(12, iso12);
				break;
			// 013 new IFA_NUMERIC ( 4, "DATE, LOCAL TRANSACTION")
			case 13:
				isoMessage.put(13, iso12.substring(0, 4));
				break;
			// 015 new IFA_NUMERIC ( 6, "DATE, SETTLEMENT")
			case 15:
				isoMessage.put(15, iso12.substring(0, 6));
				break;
			// 022 new IFA_NUMERIC ( 12, "POINT OF SERVICE ENTRY MODE")
			case 22:
				isoMessage.put(22, String.format("%12s", isoMessage.get(22)));
				break;
			// 025 new IFA_NUMERIC ( 2, "POINT OF SERVICE CONDITION CODE")
			case 25:

//					strTemp = entry.getValue();
//					isoMessage.put(25, strTemp.substring(2));
				break;
			// 026 new IFA_NUMERIC ( 2, "POINT OF SERVICE PIN CAPTURE CODE")
			case 26:

//					strTemp = entry.getValue();
//					isoMessage.put(26, strTemp.substring(2));
				break;
			// 028 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION FEE")
			case 28:

				break;
			// 029 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT FEE")
			case 29:

				break;
			// 030 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION PROCESSING FEE")
			case 30:

				break;
			// 031 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT PROCESSING FEE")
			case 31:

				break;
			// 039 new IF_CHAR ( 2, "RESPONSE CODE")
			case 39:
				builderTemp = new StringBuilder();
				builderTemp.append(0).append(isoMessage.get(39));
				isoMessage.put(39, builderTemp.toString());
				break;
			// 043 new IF_CHAR ( 40, "CARD ACCEPTOR NAME/LOCATION")
			case 43:
				isoMessage.put(40, String.format("%-99s", isoMessage.get(40)));
				break;
			// 044 new IFA_LLCHAR ( 25, "ADITIONAL RESPONSE DATA")
			case 44:
				isoMessage.put(44, String.format("%99s", isoMessage.get(44)));
				break;

			case 46:

//					strTemp = entry.getValue();
//					len = strTemp.length();
//					if (len > 25) {
//						isoMessage.put(44, strTemp.substring(0, 24));
//					} else
//						isoMessage.put(44, strTemp);
				break;

			case 53:

				break;
			case 55:

				break;

			case 56:
				strTemp = entry.getValue();
				isoMessage.put(56, String.format("%35s", isoMessage.get(90)));
				break;

			case 57:

				break;

			case 58:

				break;

			case 65:

				break;

			case 66:

				break;

			// 071 new IFA_NUMERIC ( 4, "MESSAGE NUMBER")
			case 71:
				strTemp = entry.getValue();
				isoMessage.put(71, String.format("%8s", isoMessage.get(71)));
				break;

			case 72:

				break;

			case 82:

				break;

			case 83:

				break;

			case 84:

				break;

			case 85:

				break;

			case 90:

				break;

			case 91:

				break;

			case 92:
				isoMessage.put(92, isoMessage.get(19));
				break;
			case 93:

				break;

			case 94:

				break;

			case 95:

				break;

			case 96:

				break;
			case 105:

				break;

			case 106:

				break;

			case 107:

				break;

			case 108:

				break;

			case 109:

				break;

			case 110:

				break;

			default:
				break;
			}
		}
		return isoMessage;
	}
}
