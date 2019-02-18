package com.rumango.median.iso.test;
//package com.rumango.median.iso.serviceimpl;
//
//import java.time.Year;
//import java.util.Map;
//
//public class ValidateFieldsImplBackup {
//
//	public Map<Integer, String> incoming(Map<Integer, String> isoMsg) {
//		return null;
//	}
//
//	public Map<Integer, String> outgoing(Map<Integer, String> isoMsg) {
//		Map<Integer, String> isoMessage = isoMsg;
//		int year = Year.now().getValue() % 100;
//		StringBuilder builderTemp;
//		String strTemp, iso12 = "";
//		for (Map.Entry<Integer, String> entry : isoMsg.entrySet()) {
//			int i = entry.getKey();
//			switch (i) {
//			// Replace MTI
//			case 0:
//				strTemp = entry.getValue();
//				builderTemp = new StringBuilder(strTemp);
//				builderTemp.setCharAt(0, '1');
//				isoMessage.put(0, builderTemp.toString());
//				break;
//			// 012 new IFA_NUMERIC ( 12, "TIME, LOCAL TRANSACTION")
//			case 12:
//				builderTemp = new StringBuilder();
//				builderTemp.append(year).append(isoMessage.get(13)).append(entry.getValue());
//				iso12 = builderTemp.toString();
//				isoMessage.put(12, iso12);
//				break;
//			// 013 new IFA_NUMERIC ( 4, "DATE, LOCAL TRANSACTION")
//			case 13:
//				isoMessage.put(13, iso12.substring(0, 4));
//				break;
//			// 015 new IFA_NUMERIC ( 6, "DATE, SETTLEMENT")
//			case 15:
//				isoMessage.put(15, iso12.substring(0, 6));
//				break;
//			// 022 new IFA_NUMERIC ( 12, "POINT OF SERVICE ENTRY MODE")
//			case 22:
//				isoMessage.put(22, String.format("%12s", entry.getValue()));
//				break;
//			// 025 new IFA_NUMERIC ( 2, "POINT OF SERVICE CONDITION CODE")
//			case 25:
////					strTemp = entry.getValue();
////					isoMessage.put(25, strTemp.substring(2));
//				break;
//			// 026 new IFA_NUMERIC ( 2, "POINT OF SERVICE PIN CAPTURE CODE")
//			case 26:
////					strTemp = entry.getValue();
////					isoMessage.put(26, strTemp.substring(2));
//				break;
//			// 028 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION FEE")
//			case 28:
//				break;
//			// 029 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT FEE")
//			case 29:
//				break;
//			// 030 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION PROCESSING FEE")
//			case 30:
//				break;
//			// 031 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT PROCESSING FEE")
//			case 31:
//				break;
//			// 039 new IF_CHAR ( 2, "RESPONSE CODE")
//			case 39:
//				builderTemp = new StringBuilder();
//				builderTemp.append(0).append(entry.getValue());
//				isoMessage.put(i, builderTemp.toString());
//				break;
//			// 043 new IF_CHAR ( 40, "CARD ACCEPTOR NAME/LOCATION")
//			case 43:
//				isoMessage.put(i, String.format("%-99s", entry.getValue()));
//				break;
//			// 044 new IFA_LLCHAR ( 25, "ADITIONAL RESPONSE DATA")
//			case 44:
//				isoMessage.put(i, String.format("%99s", entry.getValue()));
//				break;
//
//			case 46:
////					strTemp = entry.getValue();
////					len = strTemp.length();
////					if (len > 25) {
////						isoMessage.put(44, strTemp.substring(0, 24));
////					} else
////						isoMessage.put(44, strTemp);
//				break;
//
//			case 53:
//				break;
//			case 55:
//				break;
//
//			case 56:
//				strTemp = entry.getValue();
//				isoMessage.put(i, String.format("%35s", entry.getValue()));
//				break;
//
//			case 57:
//				break;
//
//			case 58:
//				break;
//
//			case 65:
//				break;
//
//			case 66:
//				break;
//
//			// 071 new IFA_NUMERIC ( 4, "MESSAGE NUMBER")
//			case 71:
//				strTemp = entry.getValue();
//				isoMessage.put(i, String.format("%8s", entry.getValue()));
//				break;
//
////			case 72:
////				isoMessage.unset(i);
////				break;
////
////			case 82:
////				isoMessage.unset(i);
////				break;
////
////			case 83:
////				isoMessage.unset(i);
////				break;
////
////			case 84:
////				isoMessage.unset(i);
////				break;
////
////			case 85:
////				isoMessage.unset(i);
////				break;
////
////			case 90:
////				isoMessage.unset(i);
////				break;
////
////			case 91:
////				isoMessage.unset(i);
////				break;
////
////			case 92:
////				isoMessage.put(i, isoMessage.getString(19));
////				break;
////			case 93:
////				isoMessage.unset(i);
////				break;
////
////			case 94:
////				isoMessage.unset(i);
////				break;
////
////			case 95:
////				isoMessage.unset(i);
////				break;
////
////			case 96:
////				isoMessage.unset(i);
////				break;
////			case 105:
////				isoMessage.unset(i);
////				break;
////
////			case 106:
////				isoMessage.unset(i);
////				break;
////
////			case 107:
////				isoMessage.unset(i);
////				break;
////
////			case 108:
////				isoMessage.unset(i);
////				break;
////
////			case 109:
////				isoMessage.unset(i);
////				break;
////
////			case 110:
////				isoMessage.unset(i);
////				break;
//
//			default:
//				break;
//			}
//		}
//		return isoMessage;
//
//	}
//
//	public Map<Integer, String> doValidations(Map<Integer, String> isoMsg) {
//
////		ISOMsg isoMessage = isoMsg;
////		int len;
////		StringBuilder builderTemp;
////		String strTemp, iso12 = "";
////		for (int i = 0; i <= isoMessage.getMaxField(); i++) {
////			if (isoMessage.hasField(i)) {
////				switch (i) {
////				// Replace MTI
////				case 0:
////					strTemp = entry.getValue();
////					builderTemp = new StringBuilder(strTemp);
////					builderTemp.setCharAt(0, '0');
////					isoMessage.put(0, builderTemp.toString());
////					break;
////				case 3:
////					// isoMessage.put(i, String.format("%6s", validations.validate(i,
////					// isoMessage.getString(i))));
////					break;
////				// 012 new IFA_NUMERIC ( 6, "TIME, LOCAL TRANSACTION"),
////				case 12:
////					strTemp = entry.getValue();
////					iso12 = strTemp;
////					if (strTemp.length() > 6)
////						isoMessage.put(12, strTemp.substring(6));
////					else
////						isoMessage.put(12, strTemp);
////					break;
////				// 013 new IFA_NUMERIC ( 4, "DATE, LOCAL TRANSACTION"),
////				case 13:
////					isoMessage.put(13, iso12.substring(2, 6));
////					break;
////				// 015 new IFA_NUMERIC ( 4, "DATE, SETTLEMENT"),
////				case 15:
////					strTemp = entry.getValue();
////					isoMessage.put(15, strTemp.substring(2));
////					break;
////				// 022 new IFA_NUMERIC ( 3, "POINT OF SERVICE ENTRY MODE"),
////				case 22:
////					strTemp = entry.getValue();
////					isoMessage.put(22, strTemp.substring(2));
////					break;
////				// 025 new IFA_NUMERIC ( 2, "POINT OF SERVICE CONDITION CODE")
////				case 25:
////					strTemp = entry.getValue();
////					isoMessage.put(25, strTemp.substring(2));
////					break;
////				// 026 new IFA_NUMERIC ( 2, "POINT OF SERVICE PIN CAPTURE CODE")
////				case 26:
////					strTemp = entry.getValue();
////					isoMessage.put(26, strTemp.substring(2));
////					break;
////				// 028 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION FEE")
////				case 28:
////					// isoMessage.put(i, String.format("%9s", validations.validate(i,
////					// isoMessage.getString(i))));
////					// isoMessage.unset(i);
////					break;
////				// 029 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT FEE")
////				case 29:
////					// isoMessage.put(i, String.format("%9s", validations.validate(i,
////					// isoMessage.getString(i))));
////					// isoMessage.unset(i);
////					break;
////				// 030 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION PROCESSING FEE")
////				case 30:
////					isoMessage.unset(i);
////					break;
////				// 031 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT PROCESSING FEE")
////				case 31:
////					isoMessage.unset(i);
////					break;
////				case 32:
////					// isoMessage.put(i, String.format("%11s", validations.validate(i,
////					// isoMessage.getString(i))));
////					break;
////				// 039 new IF_CHAR ( 2, "RESPONSE CODE")
////				case 39:
////					strTemp = entry.getValue();
////					isoMessage.put(39, strTemp.substring(1));
////					break;
////				// 043 new IF_CHAR ( 40, "CARD ACCEPTOR NAME/LOCATION")
////				case 43:
////					strTemp = entry.getValue();
////					len = strTemp.length();
////					if (len > 40) {
////						isoMessage.put(43, strTemp.substring(0, 39));
////					} else
////						isoMessage.put(43, strTemp);
////					break;
////				// 044 new IFA_LLCHAR ( 25, "ADITIONAL RESPONSE DATA")
////				case 44:
////					strTemp = entry.getValue();
////					len = strTemp.length();
////					if (len > 25) {
////						isoMessage.put(44, strTemp.substring(0, 24));
////					} else
////						isoMessage.put(44, strTemp);
////					break;
////
////				case 46:
////					isoMessage.unset(i);
//////					strTemp = entry.getValue();
//////					len = strTemp.length();
//////					if (len > 25) {
//////						isoMessage.put(44, strTemp.substring(0, 24));
//////					} else
//////						isoMessage.put(44, strTemp);
////					break;
////
////				case 49:
////					isoMessage.put(i, String.format("%3s", validations.validate(i, isoMessage.getString(i))));
////					// isoMessage.unset(i);
////					break;
////				case 53:
////					isoMessage.unset(i);
////					break;
////				case 55:
////					isoMessage.unset(i);
////					break;
////
////				case 56:
////					strTemp = entry.getValue();
////					isoMessage.put(90, strTemp.substring(1));
////					break;
////
////				case 57:
////					isoMessage.unset(i);
////					break;
////
////				case 58:
////					isoMessage.unset(i);
////					break;
////
////				case 65:
////					isoMessage.unset(i);
////					break;
////
////				case 66:
////					isoMessage.unset(i);
////					break;
////
////				// 071 new IFA_NUMERIC ( 4, "MESSAGE NUMBER")
////				case 71:
////					strTemp = entry.getValue();
////					isoMessage.put(71, strTemp.substring(0, 4));
////					break;
////
////				case 72:
////					isoMessage.unset(i);
////					break;
////
////				case 82:
////					isoMessage.unset(i);
////					break;
////
////				case 83:
////					isoMessage.unset(i);
////					break;
////
////				case 84:
////					isoMessage.unset(i);
////					break;
////
////				case 85:
////					isoMessage.unset(i);
////					break;
////
////				case 90:
////					isoMessage.unset(i);
////					break;
////
////				case 91:
////					isoMessage.unset(i);
////					break;
////
////				case 92:
////					strTemp = entry.getValue();
////					isoMessage.put(19, strTemp);
////					isoMessage.unset(i);
////					break;
////				case 93:
////					isoMessage.unset(i);
////					break;
////
////				case 94:
////					isoMessage.unset(i);
////					break;
////
////				case 95:
////					isoMessage.unset(i);
////					break;
////
////				case 96:
////					isoMessage.unset(i);
////					break;
////				case 105:
////					isoMessage.unset(i);
////					break;
////
////				case 106:
////					isoMessage.unset(i);
////					break;
////
////				case 107:
////					isoMessage.unset(i);
////					break;
////
////				case 108:
////					isoMessage.unset(i);
////					break;
////
////				case 109:
////					isoMessage.unset(i);
////					break;
////
////				case 110:
////					isoMessage.unset(i);
////					break;
////
////				default:
////					break;
////				}
////			}
////		}
////		packager87 = new ISO87APackager();
////		isoMessage.putPackager(packager87);
////		return isoMessage;
//		return null;
//	}
//
//	public Map<Integer, String> iso93TO87(Map<Integer, String> isoMsg) {
////		ISOMsg isoMessage = isoMsg;
////		int len;
////		StringBuilder builderTemp;
////		String strTemp, iso12 = "";
////		for (int i = 0; i <= isoMessage.getMaxField(); i++) {
////			if (isoMessage.hasField(i)) {
////				switch (i) {
////				// Replace MTI
////				case 0:
////					strTemp = entry.getValue();
////					builderTemp = new StringBuilder(strTemp);
////					builderTemp.setCharAt(0, '0');
////					isoMessage.put(0, builderTemp.toString());
////					break;
////				case 3:
////					// isoMessage.put(i, String.format("%6s", validations.validate(i,
////					// isoMessage.getString(i))));
////					break;
////				// 012 new IFA_NUMERIC ( 6, "TIME, LOCAL TRANSACTION"),
////				case 12:
////					strTemp = entry.getValue();
////					iso12 = strTemp;
////					if (strTemp.length() > 6)
////						isoMessage.put(12, strTemp.substring(6));
////					else
////						isoMessage.put(12, strTemp);
////					break;
////				// 013 new IFA_NUMERIC ( 4, "DATE, LOCAL TRANSACTION"),
////				case 13:
////					isoMessage.put(13, iso12.substring(2, 6));
////					break;
////				// 015 new IFA_NUMERIC ( 4, "DATE, SETTLEMENT"),
////				case 15:
////					strTemp = entry.getValue();
////					isoMessage.put(15, strTemp.substring(2));
////					break;
////				// 022 new IFA_NUMERIC ( 3, "POINT OF SERVICE ENTRY MODE"),
////				case 22:
////					strTemp = entry.getValue();
////					isoMessage.put(22, strTemp.substring(2));
////					break;
////				// 025 new IFA_NUMERIC ( 2, "POINT OF SERVICE CONDITION CODE")
////				case 25:
////					strTemp = entry.getValue();
////					isoMessage.put(25, strTemp.substring(2));
////					break;
////				// 026 new IFA_NUMERIC ( 2, "POINT OF SERVICE PIN CAPTURE CODE")
////				case 26:
////					strTemp = entry.getValue();
////					isoMessage.put(26, strTemp.substring(2));
////					break;
////				// 028 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION FEE")
////				case 28:
////					// isoMessage.put(i, String.format("%9s", validations.validate(i,
////					// isoMessage.getString(i))));
////					// isoMessage.unset(i);
////					break;
////				// 029 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT FEE")
////				case 29:
////					// isoMessage.put(i, String.format("%9s", validations.validate(i,
////					// isoMessage.getString(i))));
////					// isoMessage.unset(i);
////					break;
////				// 030 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION PROCESSING FEE")
////				case 30:
////					isoMessage.unset(i);
////					break;
////				// 031 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT PROCESSING FEE")
////				case 31:
////					isoMessage.unset(i);
////					break;
////				case 32:
////					// isoMessage.put(i, String.format("%11s", validations.validate(i,
////					// isoMessage.getString(i))));
////					break;
////				// 039 new IF_CHAR ( 2, "RESPONSE CODE")
////				case 39:
////					strTemp = entry.getValue();
////					isoMessage.put(39, strTemp.substring(1));
////					break;
////				// 043 new IF_CHAR ( 40, "CARD ACCEPTOR NAME/LOCATION")
////				case 43:
////					strTemp = entry.getValue();
////					len = strTemp.length();
////					if (len > 40) {
////						isoMessage.put(43, strTemp.substring(0, 39));
////					} else
////						isoMessage.put(43, strTemp);
////					break;
////				// 044 new IFA_LLCHAR ( 25, "ADITIONAL RESPONSE DATA")
////				case 44:
////					strTemp = entry.getValue();
////					len = strTemp.length();
////					if (len > 25) {
////						isoMessage.put(44, strTemp.substring(0, 24));
////					} else
////						isoMessage.put(44, strTemp);
////					break;
////
////				case 46:
////					isoMessage.unset(i);
//////					strTemp = entry.getValue();
//////					len = strTemp.length();
//////					if (len > 25) {
//////						isoMessage.put(44, strTemp.substring(0, 24));
//////					} else
//////						isoMessage.put(44, strTemp);
////					break;
////
////				case 49:
////					// isoMessage.put(i, String.format("%3s", validations.validate(i,
////					// isoMessage.getString(i))));
////					// isoMessage.unset(i);
////					break;
////				case 53:
////					isoMessage.unset(i);
////					break;
////				case 55:
////					isoMessage.unset(i);
////					break;
////
////				case 56:
////					strTemp = entry.getValue();
////					isoMessage.put(90, strTemp.substring(1));
////					break;
////
////				case 57:
////					isoMessage.unset(i);
////					break;
////
////				case 58:
////					isoMessage.unset(i);
////					break;
////
////				case 65:
////					isoMessage.unset(i);
////					break;
////
////				case 66:
////					isoMessage.unset(i);
////					break;
////
////				// 071 new IFA_NUMERIC ( 4, "MESSAGE NUMBER")
////				case 71:
////					strTemp = entry.getValue();
////					isoMessage.put(71, strTemp.substring(0, 4));
////					break;
////
////				case 72:
////					isoMessage.unset(i);
////					break;
////
////				case 82:
////					isoMessage.unset(i);
////					break;
////
////				case 83:
////					isoMessage.unset(i);
////					break;
////
////				case 84:
////					isoMessage.unset(i);
////					break;
////
////				case 85:
////					isoMessage.unset(i);
////					break;
////
////				case 90:
////					isoMessage.unset(i);
////					break;
////
////				case 91:
////					isoMessage.unset(i);
////					break;
////
////				case 92:
////					strTemp = entry.getValue();
////					isoMessage.put(19, strTemp);
////					isoMessage.unset(i);
////					break;
////				case 93:
////					isoMessage.unset(i);
////					break;
////
////				case 94:
////					isoMessage.unset(i);
////					break;
////
////				case 95:
////					isoMessage.unset(i);
////					break;
////
////				case 96:
////					isoMessage.unset(i);
////					break;
////				case 105:
////					isoMessage.unset(i);
////					break;
////
////				case 106:
////					isoMessage.unset(i);
////					break;
////
////				case 107:
////					isoMessage.unset(i);
////					break;
////
////				case 108:
////					isoMessage.unset(i);
////					break;
////
////				case 109:
////					isoMessage.unset(i);
////					break;
////
////				case 110:
////					isoMessage.unset(i);
////					break;
////
////				default:
////					break;
////				}
////			}
////		}
////		packager87 = new ISO87APackager();
////		isoMessage.putPackager(packager87);
////		return isoMessage;
//		return null;
//	}
//
//	public Map<Integer, String> iso87TO93(Map<Integer, String> isoMsg) {
//		return isoMsg;
////		ISOMsg isoMessage = isoMsg;
////		int year = Year.now().getValue() % 100;
////		StringBuilder builderTemp;
////		String strTemp, iso12 = "";
////		for (int i = 0; i <= isoMessage.getMaxField(); i++) {
////			if (isoMessage.hasField(i)) {
////				switch (i) {
////				// Replace MTI
////				case 0:
////					strTemp = entry.getValue();
////					builderTemp = new StringBuilder(strTemp);
////					builderTemp.setCharAt(0, '1');
////					isoMessage.put(0, builderTemp.toString());
////					break;
////				// 012 new IFA_NUMERIC ( 12, "TIME, LOCAL TRANSACTION")
////				case 12:
////					builderTemp = new StringBuilder();
////					builderTemp.append(year).append(isoMessage.getString(13)).append(isoMessage.getString(i));
////					iso12 = builderTemp.toString();
////					isoMessage.put(12, iso12);
////					break;
////				// 013 new IFA_NUMERIC ( 4, "DATE, LOCAL TRANSACTION")
////				case 13:
////					isoMessage.put(13, iso12.substring(0, 4));
////					break;
////				// 015 new IFA_NUMERIC ( 6, "DATE, SETTLEMENT")
////				case 15:
////					isoMessage.put(15, iso12.substring(0, 6));
////					break;
////				// 022 new IFA_NUMERIC ( 12, "POINT OF SERVICE ENTRY MODE")
////				case 22:
////					isoMessage.put(22, String.format("%12s", isoMessage.getString(i)));
////					break;
////				// 025 new IFA_NUMERIC ( 2, "POINT OF SERVICE CONDITION CODE")
////				case 25:
////					isoMessage.unset(i);
//////					strTemp = entry.getValue();
//////					isoMessage.put(25, strTemp.substring(2));
////					break;
////				// 026 new IFA_NUMERIC ( 2, "POINT OF SERVICE PIN CAPTURE CODE")
////				case 26:
////					isoMessage.unset(i);
//////					strTemp = entry.getValue();
//////					isoMessage.put(26, strTemp.substring(2));
////					break;
////				// 028 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION FEE")
////				case 28:
////					isoMessage.unset(i);
////					break;
////				// 029 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT FEE")
////				case 29:
////					isoMessage.unset(i);
////					break;
////				// 030 new IFA_AMOUNT ( 9, "AMOUNT, TRANSACTION PROCESSING FEE")
////				case 30:
////					isoMessage.unset(i);
////					break;
////				// 031 new IFA_AMOUNT ( 9, "AMOUNT, SETTLEMENT PROCESSING FEE")
////				case 31:
////					isoMessage.unset(i);
////					break;
////				// 039 new IF_CHAR ( 2, "RESPONSE CODE")
////				case 39:
////					builderTemp = new StringBuilder();
////					builderTemp.append(0).append(isoMessage.getString(i));
////					isoMessage.put(39, builderTemp.toString());
////					break;
////				// 043 new IF_CHAR ( 40, "CARD ACCEPTOR NAME/LOCATION")
////				case 43:
////					isoMessage.put(i, String.format("%-99s", isoMessage.getString(i)));
////					break;
////				// 044 new IFA_LLCHAR ( 25, "ADITIONAL RESPONSE DATA")
////				case 44:
////					isoMessage.put(i, String.format("%99s", isoMessage.getString(i)));
////					break;
////
////				case 46:
////					isoMessage.unset(i);
//////					strTemp = entry.getValue();
//////					len = strTemp.length();
//////					if (len > 25) {
//////						isoMessage.put(44, strTemp.substring(0, 24));
//////					} else
//////						isoMessage.put(44, strTemp);
////					break;
////
////				case 53:
////					isoMessage.unset(i);
////					break;
////				case 55:
////					isoMessage.unset(i);
////					break;
////
////				case 56:
////					strTemp = entry.getValue();
////					isoMessage.put(i, String.format("%35s", isoMessage.getString(90)));
////					break;
////
////				case 57:
////					isoMessage.unset(i);
////					break;
////
////				case 58:
////					isoMessage.unset(i);
////					break;
////
////				case 65:
////					isoMessage.unset(i);
////					break;
////
////				case 66:
////					isoMessage.unset(i);
////					break;
////
////				// 071 new IFA_NUMERIC ( 4, "MESSAGE NUMBER")
////				case 71:
////					strTemp = entry.getValue();
////					isoMessage.put(i, String.format("%8s", isoMessage.getString(i)));
////					break;
////
////				case 72:
////					isoMessage.unset(i);
////					break;
////
////				case 82:
////					isoMessage.unset(i);
////					break;
////
////				case 83:
////					isoMessage.unset(i);
////					break;
////
////				case 84:
////					isoMessage.unset(i);
////					break;
////
////				case 85:
////					isoMessage.unset(i);
////					break;
////
////				case 90:
////					isoMessage.unset(i);
////					break;
////
////				case 91:
////					isoMessage.unset(i);
////					break;
////
////				case 92:
////					isoMessage.put(i, isoMessage.getString(19));
////					break;
////				case 93:
////					isoMessage.unset(i);
////					break;
////
////				case 94:
////					isoMessage.unset(i);
////					break;
////
////				case 95:
////					isoMessage.unset(i);
////					break;
////
////				case 96:
////					isoMessage.unset(i);
////					break;
////				case 105:
////					isoMessage.unset(i);
////					break;
////
////				case 106:
////					isoMessage.unset(i);
////					break;
////
////				case 107:
////					isoMessage.unset(i);
////					break;
////
////				case 108:
////					isoMessage.unset(i);
////					break;
////
////				case 109:
////					isoMessage.unset(i);
////					break;
////
////				case 110:
////					isoMessage.unset(i);
////					break;
////
////				default:
////					break;
////				}
////			}
////		}
////		packager93 = new ISO93APackager();
////		isoMessage.putPackager(packager93);
////		return isoMessage;
////		return null;
////	}
//
//	}
//
//}