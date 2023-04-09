package com.ssafy.apartment.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.ssafy.apartment.dto.HouseDeal;
import com.ssafy.util.DBUtil;

public class HouseSaveLoadDaoImpl implements HouseSaveLoadDao {
	String guguncode = "11110\r\n" + "11170\r\n" + "11260\r\n" + "11410\r\n" + "11440\r\n" + "11590\r\n" + "11650\r\n"
			+ "26140\r\n" + "26170\r\n" + "26200\r\n" + "26320\r\n" + "26350\r\n" + "27140\r\n" + "27170\r\n"
			+ "27200\r\n" + "27230\r\n" + "27260\r\n" + "28140\r\n" + "28177\r\n" + "28245\r\n" + "28710\r\n"
			+ "29140\r\n" + "29155\r\n" + "29170\r\n" + "29200\r\n" + "30140\r\n" + "30170\r\n" + "30200\r\n"
			+ "31110\r\n" + "31140\r\n" + "31170\r\n" + "31200\r\n" + "31710\r\n" + "41115\r\n" + "41131\r\n"
			+ "41135\r\n" + "41173\r\n" + "41273\r\n" + "41285\r\n" + "41287\r\n" + "41290\r\n" + "41370\r\n"
			+ "41465\r\n" + "43112\r\n" + "43114\r\n" + "44133\r\n" + "45113\r\n" + "47113\r\n" + "47130\r\n"
			+ "47170\r\n" + "48125\r\n" + "48129\r\n" + "";

	private static HouseSaveLoadDao dao = new HouseSaveLoadDaoImpl();

	private HouseSaveLoadDaoImpl() {
	}

	public static HouseSaveLoadDao getDao() {
		return dao;
	}

	private DBUtil dbUtil = DBUtil.getInstance();

	@Override
	public void saveHouseDeal(List<HouseDeal> dealList) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			String sql = "insert into housedeal (no, dealAmount, dealYear, dealMonth, dealDay, area, floor, cancelDealType, aptCode) \n";
			sql += "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			for (int i = 1; i < dealList.size(); i++) {
				sql += ", \n (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			}
			pstmt = conn.prepareStatement(sql);
			int index = 0;
			HouseDeal temp = dealList.get(0);
			pstmt.setLong(++index, temp.getNo());
			pstmt.setString(++index, temp.getDealAmount());
			pstmt.setInt(++index, temp.getDealYear());
			pstmt.setInt(++index, temp.getDealMonth());
			pstmt.setInt(++index, temp.getDealDay());
			pstmt.setString(++index, temp.getArea() + "");
			pstmt.setString(++index, temp.getFloor() + "");
			pstmt.setString(++index, temp.getCancelDealType() + "");
			pstmt.setLong(++index, temp.getAptCode());
			for (int i = 1; i < dealList.size(); i++) {
				temp = dealList.get(i);
				pstmt.setLong(++index, temp.getNo());
				pstmt.setString(++index, temp.getDealAmount());
				pstmt.setInt(++index, temp.getDealYear());
				pstmt.setInt(++index, temp.getDealMonth());
				pstmt.setInt(++index, temp.getDealDay());
				pstmt.setString(++index, temp.getArea() + "");
				pstmt.setString(++index, temp.getFloor() + "");
				pstmt.setString(++index, temp.getCancelDealType() + "");
				pstmt.setLong(++index, temp.getAptCode());
			}
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void saveHouseInfo(HouseDeal houseDeal) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			// aptCode
			// 시도코드+autoIncrease
			// 46313
			String sql = "insert ignore into houseinfo (aptCode, buildYear, roadName, roadNameBonbun, roadNameBubun, roadNameSeq, roadNameBasementCode, roadNameCode, dong, bonbun, bubun, sigunguCode, eubmyundongCode, dongcode, landCode, apartmentName, jibun, lat, lng) \n";
			sql += "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n";
			pstmt = conn.prepareStatement(sql);
			int index = 0;
			pstmt.setLong(++index, houseDeal.getAptCode());
			pstmt.setInt(++index, houseDeal.getBuildYear());
			pstmt.setString(++index, houseDeal.getRoadName());
			pstmt.setString(++index, houseDeal.getRoadNameBonbun());
			pstmt.setString(++index, houseDeal.getRoadNameBubun());
			pstmt.setString(++index, houseDeal.getRoadNameSeq());
			pstmt.setString(++index, houseDeal.getRoadNameBasementCode());
			pstmt.setString(++index, houseDeal.getRoadNameCode());
			pstmt.setString(++index, houseDeal.getDong());
			pstmt.setString(++index, houseDeal.getBonbun());
			pstmt.setString(++index, houseDeal.getBubun());
			pstmt.setString(++index, houseDeal.getSigunguCode());
			pstmt.setString(++index, houseDeal.getEubmyundongCode());
			pstmt.setString(++index, houseDeal.getDongCode());
			pstmt.setString(++index, houseDeal.getLandCode());
			pstmt.setString(++index, houseDeal.getAptName());
			pstmt.setString(++index, houseDeal.getJibun());
			pstmt.setString(++index, houseDeal.getLat());
			pstmt.setString(++index, houseDeal.getLng());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(houseDeal.toString());
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public long getAptCode(HouseDeal houseDeal) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long aptCode = 0;
//		- buildYear: 건축연도
//		- apartmentName: 아파트이름
//		- jibun: 지번
//		- sigunguCode: 시군구코드
//		- eubmyundongCode: 읍면동코드
		try {
			conn = dbUtil.getConnection();
			String sql = "select aptCode from houseInfo \n";
			sql += "where apartmentName = ? and buildYear = ? \n";
			sql += "and jibun = ? and sigunguCode = ? and eubmyundongCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, houseDeal.getAptName());
			pstmt.setInt(2, houseDeal.getBuildYear());
			pstmt.setString(3, houseDeal.getJibun());
			pstmt.setString(4, houseDeal.getSigunguCode());
			pstmt.setString(5, houseDeal.getEubmyundongCode());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				aptCode = rs.getLong("aptCode");
				return aptCode;
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return aptCode;
	}

	@Override
	public long getAptCodeUpgrade(HouseDeal houseDeal) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long aptCode = 0;
//		- buildYear: 건축연도
//		- apartmentName: 아파트이름
//		- jibun: 지번
//		- sigunguCode: 시군구코드
//		- eubmyundongCode: 읍면동코드
		try {
			conn = dbUtil.getConnection();
			String sql = "select aptCode from houseInfo \n";
			sql += "where apartmentName = ? and buildYear = ? \n";
			sql += "and sigunguCode = ? and eubmyundongCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, houseDeal.getAptName());
			pstmt.setInt(2, houseDeal.getBuildYear());
			pstmt.setString(3, houseDeal.getSigunguCode());
			pstmt.setString(4, houseDeal.getEubmyundongCode());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				aptCode = rs.getLong("aptCode");
				return aptCode;
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return aptCode;
	}
	
	@Override
	public long getMaxCode(String sigunguCode) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long cnt = 0;
		try {
			conn = dbUtil.getConnection();
			String sql = "select Max(aptCode) as max from houseInfo \n";
			sql += "where sigunguCode like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sigunguCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getLong("max");
				return cnt;
			} else {
				return 0;
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public long getMaxCode(HouseDeal houseDeal) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long cnt = 0;
		try {
			conn = dbUtil.getConnection();
			String sql = "select Max(no) as max from houseDeal \n";
			sql += "where dealYear = ? and dealMonth = ? and aptCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, houseDeal.getDealYear());
			pstmt.setInt(2, houseDeal.getDealMonth());
			pstmt.setLong(3, houseDeal.getAptCode());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getLong("max");
				return cnt;
			} else {
				return 0;
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

}
