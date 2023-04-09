package com.ssafy.apartment.util;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.apartment.dto.HouseDeal;

/**
 * AptDealHistory.xml 파일에서 아파트 거래 정보를 읽어 파싱하는 핸들러 클래스
 */
public class APTDealSAXHandler extends DefaultHandler {
	/**
	 * 아파트 거래 정보를 식별하기 위한 번호로 차후 DB에서는 primary key로 대체하지만 현재 버전에서는 0번부터 순차 부여한다.
	 */
	/** 아파트 거래 정보를 담는다 */
	private List<HouseDeal> houses;
	/** 파상힌 아파트 거래 정보 */
	private HouseDeal house;
	/** 태그 바디 정보를 임시로 저장 */
	private String temp;

	public APTDealSAXHandler() {
		houses = new LinkedList<HouseDeal>();
	}

	public void startElement(String uri, String localName, String qName, Attributes att) {
		temp = "";
		if (qName.equals("item")) {
			house = new HouseDeal();
			house.setType(1);
			houses.add(house);
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if (qName.equals("지역코드")) {
			house.setCode(Integer.parseInt(temp));
		} else if (qName.equals("아파트")) {
			house.setAptName(temp.trim());
		} else if (qName.equals("법정동")) {
			house.setDong(temp.trim());
		} else if (qName.equals("거래금액")) {
			house.setDealAmount(temp.trim());
		} else if (qName.equals("건축년도")) {
			house.setBuildYear(Integer.parseInt(temp));
		} else if (qName.equals("년")) {
			house.setDealYear(Integer.parseInt(temp));
		} else if (qName.equals("월")) {
			house.setDealMonth(Integer.parseInt(temp));
		} else if (qName.equals("일")) {
			house.setDealDay(Integer.parseInt(temp));
		} else if (qName.equals("전용면적")) {
			house.setArea(Double.parseDouble(temp));
		} else if (qName.equals("지번")) {
			house.setJibun(temp);
		} else if (qName.equals("층")) {
			house.setFloor(Integer.parseInt(temp));
		} else if (qName.equals("도로명")) {
			house.setRoadName(temp.trim());
		} else if (qName.equals("도로명건물본번호코드")) {
			house.setRoadNameBonbun(temp.trim());
		} else if (qName.equals("도로명건물부번호코드")) {
			house.setRoadNameBubun(temp.trim());
		} else if (qName.equals("도로명시군구코드")) {
			house.setSigunguCode(temp.trim());
		} else if (qName.equals("도로명일련번호코드")) {
			house.setRoadNameSeq(temp.trim());
		} else if (qName.equals("도로명지상지하코드")) {
			house.setRoadNameBasementCode(temp.trim());
		} else if (qName.equals("도로명코드")) {
			house.setRoadNameCode(temp.trim());
		} else if (qName.equals("법정동본번코드")) {
			house.setBonbun(temp.trim());
		} else if (qName.equals("법정동부번코드")) {
			house.setBubun(temp.trim());
		} else if (qName.equals("법정동시군구코드")) {
			house.setSigunguCode(temp.trim());
		} else if (qName.equals("법정동읍면동코드")) {
			house.setEubmyundongCode(temp.trim());
		} else if (qName.equals("법정동지번코드")) {
			house.setLandCode(temp.trim());
		} else if (qName.equals("해제여부")) {
			house.setCancelDealType(temp.trim());
		}
	}

	public void characters(char[] ch, int start, int length) {
		temp += new String(ch, start, length);
	}

	public List<HouseDeal> getHouses() {
		return houses;
	}

	public void setHouses(List<HouseDeal> houses) {
		this.houses = houses;
	}
}
