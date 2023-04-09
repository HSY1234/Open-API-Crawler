package com.ssafy.apartment.dto;

public class LatLngDongCodeDto {
	private String lat;
	private String lng;
	private String dongCode;

	public LatLngDongCodeDto(String lat, String lng, String dongCode) {
		this.lat = lat;
		this.lng = lng;
		this.dongCode = dongCode;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	@Override
	public String toString() {
		return "LatLngDongCodeDto [lat=" + lat + ", lng=" + lng + ", dongCode=" + dongCode + "]";
	}

}
