package com.rumango.median.iso.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface IsoUtil {

	public String jsonToXml(String json);

	public String toCsv(String stringMessage, String isoVersion);

	public Map<Integer, String> unpackMessage(String stringMessage, String isoVersion)
			throws IOException, ParseException;

	public String packMessage(Map<Integer, String> isoMessage, String isoVersion) throws Exception;

	public void logISOMsg(@NotEmpty @NotNull Map<Integer, String> msg, String stringMessage);

	public String isoToJson(Map<Integer, String> msg);

	public String isoToXml(Map<Integer, String> msg);

	public Map<Integer, String> xmlToIso(String msg);

	public Map<Integer, String> jsonToIso(String msg);

}
