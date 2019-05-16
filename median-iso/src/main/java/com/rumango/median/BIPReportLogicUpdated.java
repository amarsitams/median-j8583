//package com.rumango.median;
//
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.xml.namespace.QName;
//
//import org.apache.log4j.Logger;
//
//import com.oracle.xmlns.oxp.service.v11g.publicreportservice.ArrayOfParamNameValue;
//import com.oracle.xmlns.oxp.service.v11g.publicreportservice.ArrayOfString;
//import com.oracle.xmlns.oxp.service.v11g.publicreportservice.BIPDataSource;
//import com.oracle.xmlns.oxp.service.v11g.publicreportservice.JDBCDataSource;
//import com.oracle.xmlns.oxp.service.v11g.publicreportservice.ParamNameValue;
//import com.oracle.xmlns.oxp.service.v11g.publicreportservice.PublicReportService;
//import com.oracle.xmlns.oxp.service.v11g.publicreportservice.PublicReportServiceService;
//import com.oracle.xmlns.oxp.service.v11g.publicreportservice.ReportRequest;
//import com.oracle.xmlns.oxp.service.v11g.publicreportservice.ReportResponse;
//
//public class BIPReportLogicUpdated {
//	private final static Logger logger = Logger.getLogger(BIPReportLogicUpdated.class);
//	private String bipEndPoint_ = null;
//	private String paramName_ = null;
//	private String paramValue_ = null;
//	private String reqString_ = null;
//	private String xdoFile_ = null;
//	private String userId_ = null;
//	private String pwd_ = null;
//	private int dataChunkLoad_ = -1;
//	ParamNameValue[] paramNmVals_ = null;
//
//	private String ReportFolder_ = "FCReports";
//	private String AdiceReportFolder_ = "FCReports/Advice";
//	private String bipAdviceFilePath_ = "";
//	private String bipTimeZone = null;
//
//	private void initializeWebService(String contextPath) {
//		try {
//			bipEndPoint_ = "";
//			userId_ = "";
//			pwd_ = "";
//
//			ReportFolder_ = "";
//			AdiceReportFolder_ = "BIP_ADVICE_FOLDER";
//			bipAdviceFilePath_ = "BIP_ADV_SPOOL_PATH";
//			bipTimeZone = "BIP_TIME_ZONE";
//		} catch (Exception e) {
//			logger.error("Error ", e);
//		}
//	}
//
//	private void getParamNameValues(String requestContent) {
//		String requestStringFCJ = requestContent;
//		try {
//			paramName_ = requestStringFCJ.split("!")[0];
//			paramValue_ = requestStringFCJ.split("!")[1];
//			if (requestStringFCJ.split("!").length > 2)
//				reqString_ = requestStringFCJ.split("!_@@_##_!")[1];
//		} catch (Exception e) {
//		}
//		createFCJParamVals(paramName_, paramValue_);
//	}
//
//	private void evaluateXDOFile(String functionId) {
//		String xdoName = functionId;
//		xdoFile_ = ("/" + ReportFolder_ + "/" + xdoName + "." + "xdo");
//	}
//
//	private void createFCJParamVals(String paramName, String ParamValue) {
//		int paramNameValueSize = 0;
//		String[] param_key = new String['Ǵ'];
//		String[] param_value = new String['Ǵ'];
//		String pKey = paramName;
//		String pValue = ParamValue;
//		try {
//			param_key = pKey.split("#", -1);
//			param_value = pValue.split("#", -1);
//
//			logger.info("createFCJParamVals-->Number of parameters is " + param_key.length);
//
//			for (int x = 0; x < param_key.length; x++) {
//				logger.info("createFCJParamVals-->" + param_key[x]);
//				logger.info("createFCJParamVals-->1-->" + param_value[x]);
//
//				if ((param_key[x].equals("REPFID")) && (param_value[x].contains("_@BPEL@_"))) {
//					logger.info("createFCJParamVals-->Getting Bpel Report Id");
//
//					param_value[x] = param_value[x].substring(0, param_value[x].lastIndexOf("_@BPEL@_"));
//
//					logger.info("createFCJParamVals-->Truncated Report Id-->" + param_value[x]);
//				}
//
//				String temp_var = param_value[x];
//				if (temp_var.length() != 0) {
//					paramNameValueSize++;
//				}
//			}
//			logger.info("createFCJParamVals-->Number of parameters to send " + paramNameValueSize);
//			paramNmVals_ = new ParamNameValue[paramNameValueSize];
//
//			for (int x = 0; x < param_key.length; x++) {
//				if (param_value[x].length() != 0) {
//					if ((param_key[x].equals("PM_BRANCH_DATE")) && (pKey.indexOf("EXTREPORT") == -1)) {
//						logger.info("createFCJParamVals-->formatting branch_date");
//						Date date = new Date();
//						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//						date = df.parse(param_value[x]);
//						SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//						param_value[x] = formatter.format(date).toUpperCase();
//						logger.info("createFCJParamVals-->After formatting pm_branch_date " + param_value[x]);
//					}
//					paramNameValueSize--;
//
//					logger.info("createFCJParamVals-->parameters to be set are ");
//					logger.info("createFCJParamVals-->parameter " + paramNameValueSize + " " + param_key[x] + "="
//							+ param_value[x]);
//
//					paramNmVals_[paramNameValueSize] = new ParamNameValue();
//					paramNmVals_[paramNameValueSize].setMultiValuesAllowed(false);
//					paramNmVals_[paramNameValueSize].setName(param_key[x]);
//					ArrayOfString arrayOfString = new ArrayOfString();
//					arrayOfString.getItem().add(param_value[x]);
//					paramNmVals_[paramNameValueSize].setValues(arrayOfString);
//				}
//			}
//		} catch (Exception e) {
//			logger.info("createFCJParamVals-->Exception in createFCJParamVals method " + e);
//		}
//	}
//
//	public void writeReport(ReportResponse reportResponse, String filePath, String fileName, String fileExtension) {
//		byte[] binaryBytes = null;
//		OutputStream out = null;
//		boolean isSuccess = false;
//		try {
//			logger.info("writeReport--> entered into try block");
//			binaryBytes = reportResponse.getReportBytes();
//			if (fileExtension.equalsIgnoreCase("excel"))
//				fileExtension = "xls";
//			out = new FileOutputStream(filePath + fileName + "." + fileExtension);
//			out.write(binaryBytes);
//			out.close();
//
//			logger.info("writeReport-->Report file written successfully");
//			isSuccess = true;
//			return;
//		} catch (Exception e) {
//			logger.info("Exception in writing the report file" + e);
//		} finally {
//			try {
//				if (out != null)
//					out.close();
//			} catch (Exception e) {
//				logger.error("Error ", e);
//			}
//		}
//	}
//
//	public String generateReport(String requestXml, String contextPath) {// BipProperties biProperties, String
//																			// contextPath, FCUserGlobals uc
//		String returnString = "Failed";
//		String dsn = null;
//		ReportResponse reportResp = new ReportResponse();
//		URL endPointUrl = null;
//
//		PublicReportServiceService locator = null;
//		JDBCDataSource jdbcDs = null;
//		BIPDataSource bipDs = null;
//
//		ReportRequest req = null;
//		try {
//			logger.info("generateReport-->Getting BIP properties");
//			logger.info("generateReport--> Function Id :: " + biProperties.getFunctionId());
//			logger.info("generateReport--> File Name :: " + biProperties.getReportFileName());
//			logger.info("generateReport--> File Path  :: " + biProperties.getReportFilePath());
//			logger.info("generateReport--> File Format :: " + biProperties.getReportFormat());
//			logger.info("generateReport--> File Extension is :: " + biProperties.getReportFormat());
//
//			logger.info("generateReport--> calling getParamNameValues method");
//			if ((requestXml != null) && (!requestXml.equalsIgnoreCase(""))) {
//				getParamNameValues(requestXml);
//			}
//
//			logger.info("generateReport-->Going to set web service constants");
//			initializeWebService(contextPath);
//			endPointUrl = new URL(bipEndPoint_);
//
//			locator = new PublicReportServiceService(new URL(bipEndPoint_),
//					new QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "PublicReportServiceService"));
//			PublicReportService publicReportService = locator.getPublicReportService();
//
//			req = new ReportRequest();
//
//			if ((uc.getDsnName() != null) || (uc.getDsnName().length() != 0)) {
//				dsn = uc.getDsnName();
//				logger.info("generateReport-->DSN to be selected:: " + dsn);
//
//				String BIP_DB_DRIVER = uc.getReportsDbDriver();
//				String BIP_DB_USERNAME = uc.getReportsDbUserName();
//				String BIP_DB_PASSWORD = uc.getReportsDbPwd();
//				String BIP_DB_URL = uc.getReportsUrl();
//				jdbcDs = new JDBCDataSource();
//				jdbcDs.setDataSourceName(dsn);
//				jdbcDs.setJDBCDriverClass(BIP_DB_DRIVER);
//				jdbcDs.setJDBCURL(BIP_DB_URL);
//				jdbcDs.setJDBCUserName(BIP_DB_USERNAME);
//				jdbcDs.setJDBCPassword(BIP_DB_PASSWORD);
//
//				bipDs = new BIPDataSource();
//				bipDs.setJDBCDataSource(jdbcDs);
//				req.setDynamicDataSource(bipDs);
//			}
//			logger.info("generateReport-->Evaluating XDO file");
//			req.setAttributeFormat(biProperties.getReportFormat());
//			req.setSizeOfDataChunkDownload(dataChunkLoad_);
//			req.setAttributeTimezone(bipTimeZone);
//
//			logger.info(" BIP Advice Flag --> " + biProperties.getAdvFlag());
//
//			if (biProperties.getFunctionId().toUpperCase().contains("_@BPEL@_"))
//				biProperties.setAdvFlag("Y");
//			if ((biProperties.getAdvFlag() != null) && (biProperties.getAdvFlag().equalsIgnoreCase("Y"))) {
//				if (!biProperties.getFunctionId().toUpperCase().contains("_@BPEL@_")) {
//					String responseData = biProperties.getAdvDataXml();
//					logger.info(" BIP Advice DataXml --> " + biProperties.getAdvDataXml());
//
//					byte[] b = responseData.getBytes();
//					req.setReportData(b);
//					biProperties.setReportFilePath(bipAdviceFilePath_);
//					ReportFolder_ = AdiceReportFolder_;
//				} else {
//					String responseData = reqString_;
//					logger.info("Advice DataXml --> " + reqString_);
//
//					byte[] b = responseData.getBytes();
//					req.setReportData(b);
//					biProperties.setFunctionId(biProperties.getFunctionId().substring(0,
//							biProperties.getFunctionId().toUpperCase().lastIndexOf("_@BPEL@_")));
//				}
//			} else {
//				ArrayOfParamNameValue arrParamVal = new ArrayOfParamNameValue();
//				for (int i = 0; i < paramNmVals_.length; i++) {
//					arrParamVal.getItem().add(paramNmVals_[i]);
//				}
//				req.setParameterNameValues(arrParamVal);
//			}
//			req.setAttributeLocale(uc.getLanguageISOCode());
//			evaluateXDOFile(biProperties.getFunctionId());
//			req.setReportAbsolutePath(xdoFile_);
//
//			logger.info("generateReport-->Calling Web Service");
//
//			reportResp = publicReportService.runReport(req, userId_, pwd_);
//
//			logger.info("generateReport-->Return ReportResponse: ContentType = " + reportResp.getReportContentType());
//			writeReport(reportResp, biProperties.getReportFilePath(), biProperties.getReportFileName(),
//					biProperties.getReportFormat());
//			returnString = "Success";
//		} catch (Exception e) {
//			logger.info("generateReport-->Exception occured in generateReport method " + e.getMessage());
//		}
//		return returnString;
//	}
//}
