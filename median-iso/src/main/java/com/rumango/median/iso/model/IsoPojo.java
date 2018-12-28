package com.rumango.median.iso.model;

import com.solab.iso8583.IsoType;

public class IsoPojo {
	private int length;
	private IsoType isoType;

	public IsoPojo(IsoType type, int len) {
		this.length = len;
		this.isoType = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public IsoType getIsoType() {
		return isoType;
	}

	public void setIsoType(IsoType isoType) {
		this.isoType = isoType;
	}

}
