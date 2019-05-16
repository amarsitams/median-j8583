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
//import org.apache.commons.codec.binary.Base64;
//
////import com.ofss.fcc.common.BranchConfig;
////import com.ofss.fcc.common.FBContext;
////import com.ofss.fcc.common.FCUserGlobals;
////import com.ofss.fcc.common.FCXMLConverter;
////import com.ofss.fcc.commonif.IFCConstants;
////import com.ofss.fcc.commonif.ILogger;
////import com.ofss.fcc.utility.FCSMSUtility;
//import com.oracle.xmlns.oxp.service.v11.publicreportservice.ArrayOfParamNameValue;
//import com.oracle.xmlns.oxp.service.v11.publicreportservice.ArrayOfString;
//import com.oracle.xmlns.oxp.service.v11.publicreportservice.BIPDataSource;
//import com.oracle.xmlns.oxp.service.v11.publicreportservice.JDBCDataSource;
//import com.oracle.xmlns.oxp.service.v11.publicreportservice.ParamNameValue;
//import com.oracle.xmlns.oxp.service.v11.publicreportservice.PublicReportService;
//import com.oracle.xmlns.oxp.service.v11.publicreportservice.PublicReportServiceService;
//import com.oracle.xmlns.oxp.service.v11.publicreportservice.ReportRequest;
//import com.oracle.xmlns.oxp.service.v11.publicreportservice.ReportResponse;
//
//public class BIPReportLogic {
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
//	private FBContext fbContext = null;
//
//	public BIPReportLogic(FBContext fbContext) {
//		this.fbContext = fbContext;
//	}
//
//	private static void dbg(FBContext fbContext1, String msg) {
//		fbContext1.getBrnLogger().dbg("BIPReportLogic." + msg);
//	}
//
//	private void initializeWebService(String contextPath) {
//		try {
//			dbg(fbContext, "initializeWebService-->In initializeWebService");
//			bipEndPoint_ = BranchConfig.getInstance().getConfigValue("BIP_END_POINT");
//			userId_ = new String(FCSMSUtility.decrypt(
//					Base64.decodeBase64(BranchConfig.getInstance().getConfigValue("BIP_USERNAME").getBytes()),
//					IFCConstants.seed_enc_array));
//			pwd_ = new String(FCSMSUtility.decrypt(
//					Base64.decodeBase64(BranchConfig.getInstance().getConfigValue("BIP_PASSWORD").getBytes()),
//					IFCConstants.seed_enc_array));
//
//			dbg(fbContext, "Bip End Point is :: " + bipEndPoint_);
//
//			ReportFolder_ = BranchConfig.getInstance().getConfigValue("BIP_REPORT_FOLDER");
//			AdiceReportFolder_ = BranchConfig.getInstance().getConfigValue("BIP_ADVICE_FOLDER");
//			bipAdviceFilePath_ = BranchConfig.getInstance().getConfigValue("BIP_ADV_SPOOL_PATH");
//
//			bipTimeZone = BranchConfig.getInstance().getConfigValue("BIP_TIME_ZONE");
//
//			dbg(fbContext, "ReportFolder_ is :: " + ReportFolder_);
//			dbg(fbContext, "AdiceReportFolder_ is :: " + AdiceReportFolder_);
//			dbg(fbContext, "bipAdviceFilePath_ is :: " + bipAdviceFilePath_);
//		} catch (Exception e) {
//			dbg(fbContext, "initializeWebService-->Exception occured in initializeWebService" + e.getMessage());
//
//			fbContext.getBrnLogger().writeStack(fbContext.getUserID(), e);
//		}
//	}
//
//	private void getParamNameValues(String requestContent) {
//		String requestStringFCJ = requestContent;
//
//		dbg(fbContext, "getParamNameValues-->before splitting");
//		try {
//			paramName_ = requestStringFCJ.split("!")[0];
//			paramValue_ = requestStringFCJ.split("!")[1];
//			if (requestStringFCJ.split("!").length > 2)
//				reqString_ = requestStringFCJ.split("!_@@_##_!")[1];
//		} catch (Exception e) {
//			dbg(fbContext, "getParamNameValues-->error in spliting" + e);
//		}
//		dbg(fbContext, "getParamNameValues-->calling createFCJParamVals");
//		createFCJParamVals(paramName_, paramValue_);
//	}
//
//	private void evaluateXDOFile(String functionId) {
//		String xdoName = functionId;
//		xdoFile_ = ("/" + ReportFolder_ + "/" + xdoName + "." + "xdo");
//		dbg(fbContext, "evaluateXDOFile-->XDO file for report is " + xdoFile_);
//	}
//
//	private void createFCJParamVals(String paramName, String ParamValue) {
//		dbg(fbContext, "createFCJParamVals-->Just Entered");
//
//		int paramNameValueSize = 0;
//		String[] param_key = new String['Ǵ'];
//		String[] param_value = new String['Ǵ'];
//		String pKey = paramName;
//		String pValue = ParamValue;
//		try {
//			param_key = pKey.split("#", -1);
//			param_value = pValue.split("#", -1);
//
//			dbg(fbContext, "createFCJParamVals-->Number of parameters is " + param_key.length);
//
//			for (int x = 0; x < param_key.length; x++) {
//				dbg(fbContext, "createFCJParamVals-->" + param_key[x]);
//				dbg(fbContext, "createFCJParamVals-->1-->" + param_value[x]);
//
//				if ((param_key[x].equals("REPFID")) && (param_value[x].contains("_@BPEL@_"))) {
//					dbg(fbContext, "createFCJParamVals-->Getting Bpel Report Id");
//
//					param_value[x] = param_value[x].substring(0, param_value[x].lastIndexOf("_@BPEL@_"));
//
//					dbg(fbContext, "createFCJParamVals-->Truncated Report Id-->" + param_value[x]);
//				}
//
//				String temp_var = param_value[x];
//				if (temp_var.length() != 0) {
//					paramNameValueSize++;
//				}
//			}
//			dbg(fbContext, "createFCJParamVals-->Number of parameters to send " + paramNameValueSize);
//
//			paramNmVals_ = new ParamNameValue[paramNameValueSize];
//
//			for (int x = 0; x < param_key.length; x++) {
//				if (param_value[x].length() != 0) {
//					if ((param_key[x].equals("PM_BRANCH_DATE")) && (pKey.indexOf("EXTREPORT") == -1)) {
//						dbg(fbContext, "createFCJParamVals-->formatting branch_date");
//
//						Date date = new Date();
//						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//						date = df.parse(param_value[x]);
//						SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//
//						param_value[x] = formatter.format(date).toUpperCase();
//						dbg(fbContext, "createFCJParamVals-->After formatting pm_branch_date " + param_value[x]);
//					}
//
//					paramNameValueSize--;
//
//					dbg(fbContext, "createFCJParamVals-->parameters to be set are ");
//					dbg(fbContext, "createFCJParamVals-->parameter " + paramNameValueSize + " " + param_key[x] + "="
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
//			dbg(fbContext, "createFCJParamVals-->Exception in createFCJParamVals method " + e);
//
//			fbContext.getBrnLogger().writeStack(fbContext.getUserID(), e);
//		}
//	}
//
//	public void writeReport(ReportResponse reportResponse, String filePath, String fileName, String fileExtension) {
//		byte[] binaryBytes = null;
//		OutputStream out = null;
//		boolean isSuccess = false;
//		try {
//			dbg(fbContext, "writeReport--> entered into try block");
//			binaryBytes = reportResponse.getReportBytes();
//			if (fileExtension.equalsIgnoreCase("excel"))
//				fileExtension = "xls";
//			out = new FileOutputStream(filePath + fileName + "." + fileExtension);
//
//			out.write(binaryBytes);
//			out.close();
//
//			dbg(fbContext, "writeReport-->Report file written successfully");
//			isSuccess = true;
//			return;
//		} catch (Exception e) {
//			dbg(fbContext, "Exception in writing the report file" + e);
//			fbContext.getBrnLogger().writeStack(fbContext.getUserID(), e);
//		} finally {
//			try {
//				if (out != null)
//					out.close();
//			} catch (Exception e) {
//				fbContext.getBrnLogger().writeStack(fbContext.getUserID(), e);
//			}
//		}
//	}
//
//	public String generateReport(String requestXml, BipProperties biProperties, String contextPath, FCUserGlobals uc) {
//		dbg(fbContext, "generateReport-->Just Entered");
//
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
//		FCXMLConverter fcxmlconverter = new FCXMLConverter();
//		try {
//			dbg(fbContext, "generateReport-->Getting BIP properties");
//			dbg(fbContext, "generateReport--> Function Id :: " + biProperties.getFunctionId());
//			dbg(fbContext, "generateReport--> File Name :: " + biProperties.getReportFileName());
//			dbg(fbContext, "generateReport--> File Path  :: " + biProperties.getReportFilePath());
//			dbg(fbContext, "generateReport--> File Format :: " + biProperties.getReportFormat());
//			dbg(fbContext, "generateReport--> File Extension is :: " + biProperties.getReportFormat());
//
//			dbg(fbContext, "generateReport--> calling getParamNameValues method");
//
//			if ((requestXml != null) && (!requestXml.equalsIgnoreCase(""))) {
//				getParamNameValues(requestXml);
//			}
//
//			dbg(fbContext, "generateReport-->Going to set web service constants");
//			initializeWebService(contextPath);
//			endPointUrl = new URL(bipEndPoint_);
//
//			locator = new PublicReportServiceService(new URL(bipEndPoint_), new QName(
//					"http://xmlns.oracle.com/oxp/service/v11/PublicReportService", "PublicReportServiceService"));
//			PublicReportService publicReportService = locator.getPublicReportServiceV11();
//
//			req = new ReportRequest();
//
//			if ((uc.getDsnName() != null) || (uc.getDsnName().length() != 0)) {
//				dsn = uc.getDsnName();
//				dbg(fbContext, "generateReport-->DSN to be selected:: " + dsn);
//				jdbcDs = new JDBCDataSource();
//				jdbcDs.setDataSourceName(dsn);
//
//				bipDs = new BIPDataSource();
//				bipDs.setJDBCDataSource(jdbcDs);
//				req.setDynamicDataSource(bipDs);
//			}
//			dbg(fbContext, "generateReport-->Evaluating XDO file");
//			req.setAttributeFormat(biProperties.getReportFormat());
//			req.setSizeOfDataChunkDownload(dataChunkLoad_);
//			req.setAttributeTimezone(bipTimeZone);
//
//			dbg(fbContext, " BIP Advice Flag --> " + biProperties.getAdvFlag());
//			if (biProperties.getFunctionId().toUpperCase().contains("_@BPEL@_"))
//				biProperties.setAdvFlag("Y");
//			if ((biProperties.getAdvFlag() != null) && (biProperties.getAdvFlag().equalsIgnoreCase("Y"))) {
//				if (!biProperties.getFunctionId().toUpperCase().contains("_@BPEL@_")) {
//					String responseData = biProperties.getAdvDataXml();
//					dbg(fbContext, " BIP Advice DataXml --> " + biProperties.getAdvDataXml());
//
//					byte[] b = responseData.getBytes();
//					req.setReportData(b);
//					biProperties.setReportFilePath(bipAdviceFilePath_);
//					ReportFolder_ = AdiceReportFolder_;
//				} else {
//					String responseData = fcxmlconverter.parseXml(reqString_);
//					dbg(fbContext, "Advice DataXml --> " + responseData);
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
//			dbg(fbContext, "generateReport-->Calling Web Service");
//
//			reportResp = publicReportService.runReport(req, userId_, pwd_);
//
//			dbg(fbContext,
//					"generateReport-->Return ReportResponse: ContentType = " + reportResp.getReportContentType());
//			writeReport(reportResp, biProperties.getReportFilePath(), biProperties.getReportFileName(),
//					biProperties.getReportFormat());
//
//			returnString = "Success";
//		} catch (Exception e) {
//			dbg(fbContext, "generateReport-->Exception occured in generateReport method " + e.getMessage());
//
//			fbContext.getBrnLogger().writeStack(fbContext.getUserID(), e);
//		}
//		return returnString;
//	}
//}
