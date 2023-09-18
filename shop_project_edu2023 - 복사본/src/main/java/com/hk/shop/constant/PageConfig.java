package com.hk.shop.constant;


public enum PageConfig {
	ROWRANGEMNG(10),
	ROWRANGEMAIN(12)
	;

	private final int label;
	
	PageConfig(int label) {
		this.label=label;
	}
	
	public int label() {
		return label;
	}
}
