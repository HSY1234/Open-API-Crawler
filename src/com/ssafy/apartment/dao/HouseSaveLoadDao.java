package com.ssafy.apartment.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.apartment.dto.HouseDeal;

public interface HouseSaveLoadDao {
	public void saveHouseDeal(List<HouseDeal> dealList) throws SQLException;

	public void saveHouseInfo(HouseDeal houseDeal) throws SQLException;

	public long getAptCode(HouseDeal houseDeal) throws SQLException;

	public long getMaxCode(String sigunguCode) throws SQLException;

	public long getMaxCode(HouseDeal houseDeal) throws SQLException;
	public long getAptCodeUpgrade(HouseDeal houseDeal) throws SQLException;
}
