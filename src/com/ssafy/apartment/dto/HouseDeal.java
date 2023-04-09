package com.ssafy.apartment.dto;

public class HouseDeal {

	public static String APT_DEAL = "아파트 매매";
	public static String APT_RENT = "아파트 전월세";
	public static String HOUSE_DEAL = "주택 매매";
	public static String HOUSE_RENT = "주택 전월세";

	private long aptCode;

	/** 거래 정보를 식별할 번호 */
	private long no;
	/** 법정 동명 */
	private String dong;
	/** 아파트 이름 */
	private String aptName;
	/** 법정 동코드 */
	private int code;
	/** 거래 금액 */
	private String dealAmount;
	/** 건축 연도 */
	private int buildYear;
	/** 거래 연도 */
	private int dealYear;
	/** 거래 월 */
	private int dealMonth;
	/** 거래 일 */
	private int dealDay;
	/** 전용면적 */
	private double area;
	/** 층 */
	private int floor;
	/** 지번 */
	/** */
	/**
	 * type 1 : 아파트 매매 2 : 연립 다세세 매매 3 : 아파트 전월세 4 : 연립 다세세 전월세
	 */
	private int type;
	private String rentMoney;
	private String dongCode;
	private String img;
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String roadNameSeq;
	private String roadNameBasementCode;
	private String roadNameCode;
	private String bonbun;
	private String bubun;
	private String sigunguCode;
	private String eubmyundongCode;
	private String landCode;

	private String lat;
	private String lng;
	private String jibun;

	private String cancelDealType;

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}

	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}

	public String getRoadNameBubun() {
		return roadNameBubun;
	}

	public void setRoadNameBubun(String roadNameBubun) {
		this.roadNameBubun = roadNameBubun;
	}

	public String getRoadNameSeq() {
		return roadNameSeq;
	}

	public void setRoadNameSeq(String roadNameSeq) {
		this.roadNameSeq = roadNameSeq;
	}

	public String getRoadNameBasementCode() {
		return roadNameBasementCode;
	}

	public void setRoadNameBasementCode(String roadNameBasementCode) {
		this.roadNameBasementCode = roadNameBasementCode;
	}

	public String getRoadNameCode() {
		return roadNameCode;
	}

	public void setRoadNameCode(String roadNameCode) {
		this.roadNameCode = roadNameCode;
	}

	public String getBonbun() {
		return bonbun;
	}

	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}

	public String getBubun() {
		return bubun;
	}

	public void setBubun(String bubun) {
		this.bubun = bubun;
	}

	public String getSigunguCode() {
		return sigunguCode;
	}

	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}

	public String getEubmyundongCode() {
		return eubmyundongCode;
	}

	public void setEubmyundongCode(String eubmyundongCode) {
		this.eubmyundongCode = eubmyundongCode;
	}

	public String getLandCode() {
		return landCode;
	}

	public void setLandCode(String landCode) {
		this.landCode = landCode;
	}

	public HouseDeal() {
	}

	public HouseDeal(long no) {
		super();
		this.no = no;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
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

	public String getCancelDealType() {
		return cancelDealType;
	}

	public void setCancelDealType(String cancelDealType) {
		this.cancelDealType = cancelDealType;
	}

	@Override
	public String toString() {
		return "HouseDeal [no=" + no + ", dong=" + dong + ", aptName=" + aptName + ", code=" + code + ", dealAmount="
				+ dealAmount + ", buildYear=" + buildYear + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth
				+ ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", type=" + type + ", rentMoney="
				+ rentMoney + ", dongCode=" + dongCode + ", img=" + img + ", roadName=" + roadName + ", roadNameBonbun="
				+ roadNameBonbun + ", roadNameBubun=" + roadNameBubun + ", roadNameSeq=" + roadNameSeq
				+ ", roadNameBasementCode=" + roadNameBasementCode + ", roadNameCode=" + roadNameCode + ", bonbun="
				+ bonbun + ", bubun=" + bubun + ", sigunguCode=" + sigunguCode + ", eubmyundongCode=" + eubmyundongCode
				+ ", landCode=" + landCode + ", lat=" + lat + ", lng=" + lng + ", jibun=" + jibun + ", cancelDealType="
				+ cancelDealType + "]";
	}

	public long getAptCode() {
		return aptCode;
	}

	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}

}
