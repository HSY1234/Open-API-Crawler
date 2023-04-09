package com.ssafy.apartment.service;

import java.io.IOException;

import com.ssafy.apartment.dto.LatLngDongCodeDto;

public interface HouseSaveLoadService {
	public void run(String target);

	public void saveData(String localCode, String gugunName, String yearMonth) throws IOException;

	public LatLngDongCodeDto getDataFromKakao(String houseAddress);
}
