package com.ssafy.apartment;

import com.ssafy.apartment.service.HouseSaveLoadService;
import com.ssafy.apartment.service.HouseSaveLoadServiceImpl;
import com.ssafy.util.LocalData;
import com.ssafy.util.SettingData;

public class Main {
	// DB에 저장하는 DAO
	private static HouseSaveLoadService service = HouseSaveLoadServiceImpl.getService();

	public static void main(String[] args) {
		// 공공데이터 서비스 키 설정
		//SettingData.aptServiceKey = "여기에 decoded 공공 데이터 서비스 키 입력";

		// 카카오 RestApi 서비스 키 설정
		//SettingData.kakaoRestApiKey = "여기에 카카오 서비스 키 입력";

		// LocalData에서 지역을 가져와 던져주면 된다.
		// 지역은 + 연산으로도 여러개를 한 번에 세팅할 수 있다.
		String local = LocalData.서울1;
		
		
		
		// 시작 년월 설정
		// 세팅 안할 시 201901로 디폴트
		SettingData.startDate = "202201";

		// 끝나는 년월 설정
		// 세팅 안할 시 202204로 디폴트
		SettingData.endDate = "202204";

		// 시작
		service.run(local);
	}
}
