package com.ssafy.apartment.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.xml.sax.SAXException;

import com.ssafy.apartment.dao.HouseSaveLoadDao;
import com.ssafy.apartment.dao.HouseSaveLoadDaoImpl;
import com.ssafy.apartment.dto.HouseDeal;
import com.ssafy.apartment.dto.LatLngDongCodeDto;
import com.ssafy.apartment.util.APTDealSAXHandler;
import com.ssafy.util.SettingData;
import com.ssafy.util.LocalData;

public class HouseSaveLoadServiceImpl implements HouseSaveLoadService {
	// DB에 저장하는 DAO
	private static HouseSaveLoadDao dao = HouseSaveLoadDaoImpl.getDao();

	private static HouseSaveLoadService service = new HouseSaveLoadServiceImpl();

	private HouseSaveLoadServiceImpl() {
	};

	public static HouseSaveLoadService getService() {
		return service;
	}

	@Override
	public void run(String target) {
		// 각각 지역코드, 년, 월을 저장하는 리스트.
		ArrayList<String[]> localList = new ArrayList<>();
		// ArrayList<String> yearList = new ArrayList<>();
		// ArrayList<String> monthList = new ArrayList<>();
		// 데이터 읽어오는 작업.
		// 스트링 리더로 읽어와 배열에 저장.
		// TODO: 이 자리에 지역을 넣으면 됨.
		String local = target;
		BufferedReader br = new BufferedReader(new StringReader(local));

		// 읽어올 데이터 임시 저장 String
		String str = "";
		try {
			while ((str = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(str, ",");
				localList.add(new String[] { st.nextToken(), st.nextToken() });
			}
//			br = new BufferedReader(new StringReader(LocalData.monthData));
//			while ((str = br.readLine()) != null) {
//				monthList.add(str);
//			}
//
//			br = new BufferedReader(new StringReader(LocalData.yearData));
//			while ((str = br.readLine()) != null) {
//				yearList.add(str);
//			}
			// 지금 현대 년월을 가져와서
			// 만약 endDate가 없거나 더 클 경우
			// 종료할 수 있도록 한다.
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			int yearVal = cal.get(Calendar.YEAR);
			String dateVal = "" + yearVal + "" + (month < 10 ? "0" + month + "" : month) + "";

			// 진보된 반복문.
			// 사용자가 입력한 날짜를 바탕으로 데이터를 가져온다.
			String startDate = SettingData.startDate;
			String endDate = SettingData.endDate;
			while (true) {
				if (startDate.equals(dateVal) || startDate.equals(endDate)) {
					System.out.println("==================== 종료 ===================");
					break;
				}
				System.out.println("\n================ " + startDate + " 시작 ================");
				for (int i = 0; i < localList.size(); i++) {
					saveData(localList.get(i)[0], localList.get(i)[1], startDate);
					// 각 지역이 끝날때마다 알려줌.
					System.out.println(
							startDate + " " + localList.get(i)[1] + " 완료! (" + (i + 1) + "/" + localList.size() + "개)");
				}
				int startYear = Integer.parseInt(startDate.substring(0, 4));
				int startMonth = Integer.parseInt(startDate.substring(4, 6));
				boolean yearChange = false;
				if (startMonth + 1 > 12) {
					yearChange = true;
					startMonth = 1;
				} else {
					startMonth += 1;
				}
				if (yearChange)
					startYear += 1;
				startDate = "" + startYear + "" + (startMonth < 10 ? "0" + startMonth : startMonth) + "";
			}

			// 2019년 1월 ~ 2021년 12월까지 전국 아파트 데이터 저장.
//			a: for (int y = 0; y < yearList.size(); y++) {
//				String year = yearList.get(y);
//				for (int m = 0; m < monthList.size(); m++) {
//					if (year.equals(yearVal + "")) {
//						if (m == month)
//							break a;
//					}
//					String yearMonth = year + monthList.get(m);
//					for (int i = 0; i < localList.size(); i++) {
//						saveData(localList.get(i)[0], yearMonth);
//						// 각 지역이 끝날때마다 알려줌.
//						System.out.println(yearMonth + " " + localList.get(i)[1] + " 완료! (" + (i + 1) + "/"
//								+ localList.size() + "개)");
//					}
//				}
//			}
			// 2022년 1월 ~ 2022년 3월까지의 데이터 저장.
//			for (int m = 0; m < 3; m++) {
//				String yearMonth = "2022" + monthList.get(m);
//				for (int i = 0; i < localList.size(); i++) {
//					saveData(localList.get(i)[0], yearMonth);
//					// 각 지역이 끝날때마다 알려줌.
//					System.out.println(localList.get(i)[1] + " 완료! (" + (i + 1) + "/" + localList.size() + "개)");
//				}
//			}

			// 현재는 특정한 날짜만 뽑을 수 있도록 테스트.
//			for (int i = 0; i < localList.size(); i++) {
//				saveData(localList.get(i)[0], "");
//				// 각 지역이 끝날때마다 알려줌.
//				System.out.println(localList.get(i)[1] + " 완료! (" + (i + 1) + "/" + localList.size() + "개)");
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveData(String localCode, String gugunName, String yearMonth) throws IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// 검색할 지역 코드
		String gugunCode = localCode;

		// 최대 2000개로 최대한 많이 뽑기
		String numOfRows = SettingData.SIZE_OF_ROWS;

		// 날짜 자동 입력
		String date = yearMonth;

		// 공공데이터 서비스 키 설정
		String serviceKey = SettingData.aptServiceKey;

		// 공공데이터 URL을 빌드한다.
		// 서비스키, 날짜, 갯수, 지역코드를 세팅한다.
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + serviceKey);
		urlBuilder.append(
				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(gugunCode, "UTF-8")); /* 동코드 검색 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode(numOfRows, "UTF-8")); /* 동코드 검색 */
		urlBuilder.append(
				"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /* 거래 년월 검색 */

		// URL 생성
		URL url = new URL(urlBuilder.toString());

		// parser 객체 생성.
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			// 기존 핸들러 사용.
			APTDealSAXHandler handler = new APTDealSAXHandler();
			parser.parse(url.openConnection().getInputStream(), handler);
			// 저장할 데이터 DTO 리스트. 핸들러로부터 xml 파싱한 데이터를 저장.
			List<HouseDeal> list = handler.getHouses();
			if (list.size() > 0) {
				int cnt = 0;
				for (HouseDeal houseDeal : list) {
					// 위도, 경도, 동코드 검색을 위해 동+지번으로 주소를 생성.
					// 하였으나 일부 지번이 오류가 난 경우가 있어
					// 도로명 + 도로명건물본번호코드 - 도로명건물부번호코드로 변경.
					String houseAddress = gugunName + " " + houseDeal.getRoadName() + houseDeal.getRoadNameBonbun()
							+ "-" + houseDeal.getRoadNameBubun();
					// 카카오 REST API를 통해 위도, 경도, 동코드를 받아온다.
					LatLngDongCodeDto data = getDataFromKakao(houseAddress);

					// 만약 도로명 주소가 오류일 경우
					// 법정동 주소로 받아온다.
					if (data == null) {
						houseAddress = gugunName + " " + houseDeal.getDong() + " " + houseDeal.getJibun();
						data = getDataFromKakao(houseAddress);
					}
					if (data == null) {
						houseAddress = gugunName + " " + houseDeal.getDong();
						data = getDataFromKakao(houseAddress);
					}
					houseDeal.setLat(data.getLat());
					houseDeal.setLng(data.getLng());
					houseDeal.setDongCode(data.getDongCode());
					// 위도, 경도, 동코드를 세팅.

					long aptCode = dao.getMaxCode(houseDeal.getSigunguCode());
					if (aptCode == 0) {
						aptCode = Long.parseLong(houseDeal.getSigunguCode() + "000000000");
					}
					houseDeal.setAptCode(aptCode + 1L);

					// 각각의 집을 db에 저장.
					dao.saveHouseInfo(houseDeal);
					houseDeal.setAptCode(dao.getAptCode(houseDeal));
					if (houseDeal.getAptCode() == 0L)
						houseDeal.setAptCode(dao.getAptCodeUpgrade(houseDeal));
					long no = dao.getMaxCode(houseDeal);
					if (no == 0) {
						no = Long.parseLong(houseDeal.getSigunguCode() + "" + (houseDeal.getDealYear() - 2000)
								+ (houseDeal.getDealMonth() < 10 ? "0" + houseDeal.getDealMonth()
										: houseDeal.getDealMonth() + "")
								+ "000000");
					}
					houseDeal.setNo(no + ++cnt);
				}
				// 리스트를 db에 저장.
				dao.saveHouseDeal(list);
			}
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public LatLngDongCodeDto getDataFromKakao(String houseAddress) {
		// 리턴 객체.
		LatLngDongCodeDto result = null;

		// 키값.
		String restApiKey = SettingData.kakaoRestApiKey;

		HttpURLConnection conn = null;
		StringBuffer response = new StringBuffer();

		// URL 세팅
		// 인증키 - KakaoAK하고 한 칸 띄워주셔야해요!
		String auth = "KakaoAK " + restApiKey;
		String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?&analyze_type=exact&page=1&size=10&query=";
		// URL 설정
		try {
			apiUrl += URLEncoder.encode(houseAddress, "UTF-8");

			URL kakaourl = new URL(apiUrl);

			conn = (HttpURLConnection) kakaourl.openConnection();

			// Request 형식 설정
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Requested-With", "curl");
			conn.setRequestProperty("Authorization", auth);

			// request에 JSON data 준비
			conn.setDoOutput(true);

			// 보내고 결과값 받기
			int responseCode = conn.getResponseCode();
			if (responseCode == 400) {
				System.out.println("400:: 해당 명령을 실행할 수 없음");
			} else if (responseCode == 401) {
				System.out.println("401:: Authorization가 잘못됨");
			} else if (responseCode == 500) {
				System.out.println("500:: 서버 에러, 문의 필요");
			} else { // 성공 후 응답 JSON 데이터받기

				Charset charset = Charset.forName("UTF-8");
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));

				String inputLine;
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}

				String lat = ""; // 37.xx
				String lng = ""; // 127.xx
				String dongCode = ""; // 법정동코드
				JSONObject jObj = (JSONObject) JSONValue.parse(response.toString());
				JSONObject meta = (JSONObject) jObj.get("meta");
				long size = (long) meta.get("total_count");
				// 데이터가 존재할 경우.
				if (size > 0) {
					JSONArray jArray = (JSONArray) jObj.get("documents");
					JSONObject subJobj = (JSONObject) jArray.get(0);
					// 도로명 주소
					JSONObject roadAddress = (JSONObject) subJobj.get("road_address");
					// 기존 주소
					JSONObject address = (JSONObject) subJobj.get("address");

					// 동코드는 기존 주소 데이터 안에 있으므로.
					if (address != null) {
						dongCode = (String) address.get("b_code");
					}

					// 도로명 주소가 null 이면
					// 기존 주소로부터 lat, lng를 가져온다.
					if (roadAddress == null) {
						JSONObject subsubJobj = (JSONObject) subJobj.get("address");
						lng = (String) subsubJobj.get("x");
						lat = (String) subsubJobj.get("y");
					} else {
						// 도로명 주소가 존재하면.
						lng = (String) roadAddress.get("x");
						lat = (String) roadAddress.get("y");
					}

					// 만약 빈값일 경우
					if (lng.equals("") || lng == null || lat.equals("") || lat == null) {
						// 사이즈가 0보다 크므로 다음 객체로부터 주소와 lat, lng를 받아온다.
						subJobj = (JSONObject) jArray.get(1);
						subJobj = (JSONObject) subJobj.get("address");
						lng = (String) subJobj.get("x");
						lat = (String) subJobj.get("y");
						dongCode = (String) subJobj.get("b_code");
					}
					// 반환할 객체 생성.
					result = new LatLngDongCodeDto(lat, lng, dongCode);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
