package com.rumango.median.iso.service;

import java.util.Map;

public interface ConvertIso {
	public Map<Integer, String> iso93TO87(Map<Integer, String> isoMsg);

	public Map<Integer, String> iso87TO93(Map<Integer, String> isoMsg);

	public Map<Integer, String> iso87ToGeneric(Map<Integer, String> isoMsg);

	public Map<Integer, String> genericTo87(Map<Integer, String> isoMsg);

	public Map<Integer, String> iso93ToGeneric(Map<Integer, String> isoMsg);

	public Map<Integer, String> genericTo93(Map<Integer, String> isoMsg);
}