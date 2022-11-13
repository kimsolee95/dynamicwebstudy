package dynamicwebpractice.test.openapi.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dynamicwebpractice.test.openapi.dto.MyLocHistory;
import dynamicwebpractice.test.openapi.dto.Row;
import dynamicwebpractice.test.openapi.dto.WifiInfo;

public class DBService {

	/*
	 * DB connection 객체 생성
	 * */
	public Connection getDBConnection() {
		
		Connection conn = null;
		String dbFile = "C:\\dynamicwebpractice\\test1.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			return conn;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/*
	 * 내 주변 wifi 조회 및 히스토리 저장
	 * */
	public List<Row> getNearWifiList(String lat, String lnt) {
		//1. near list select
		List<Row> NearWifiList = dbSelectByMyLoc(lat, lnt);
		
		if (NearWifiList.size() != 0 && !NearWifiList.isEmpty() && NearWifiList != null) {
			//2. history insert
			myLocHistoryInsert(lat, lnt);
		}
		
		return NearWifiList;
	}
	
	/*
	 * 내 주변 wifi 조회
	 * */
	public List<Row> dbSelectByMyLoc(String lat, String lnt) {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		List<Row> wifiInfoList = new ArrayList<>();
		
		
		try {
			conn = getDBConnection();
			
			String sql = "SELECT A.* FROM PUBLIC_WIFY_INFO A ORDER BY ABS(A.LAT - ?) * ABS(A.LAT - ?) + ABS(A.LNT - ?) * ABS(A.LNT - ?)" +
					" limit 1, 100";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, lat);
			preparedStatement.setString(2, lat);
			preparedStatement.setString(3, lnt);
			preparedStatement.setString(4, lnt);	
			
			rs = preparedStatement.executeQuery();
			
	        while (rs.next()) {
	        	Row row = new Row();
	        	row.setXSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"));
	        	row.setXSwifiWrdofc(rs.getString("X_SWIFI_WRDOFC"));
	        	row.setXSwifiMainNm(rs.getString("X_SWIFI_MAIN_NM"));
	        	row.setXSwifiAdres1(rs.getString("X_SWIFI_ADRES1"));
	        	row.setXSwifiAdres2(rs.getString("X_SWIFI_ADRES2"));
	        	row.setXSwifiInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
	        	row.setXSwifiInstlTy(rs.getString("X_SWIFI_INSTL_TY"));
	        	row.setXSwifiInstlMby(rs.getString("X_SWIFI_INSTL_MBY"));
	        	row.setXSwifiSvcSe(rs.getString("X_SWIFI_SVC_SE"));
	        	row.setXSwifiCmcwr(rs.getString("X_SWIFI_CMCWR"));
	        	row.setXSwifiCnstcYear(rs.getString("X_SWIFI_CNSTC_YEAR"));
	        	row.setXSwifiInoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
	        	row.setXSwifiRemars3(rs.getString("X_SWIFI_REMARS3"));
	        	row.setLat(rs.getString("LAT"));
	        	row.setLnt(rs.getString("LNT"));
	        	row.setWorkDttm(rs.getString("WORK_DTTM"));
	        	wifiInfoList.add(row);
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		return wifiInfoList;
	}
	
	/*
	 * 서울시 공공 WIFI API 응답값 일괄 저장
	 * */
	public long dbInsert(WifiInfo wifiInfo) {

		long result = 0;
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getDBConnection();
			
			String sql = "insert or replace into PUBLIC_WIFY_INFO (" +
							"X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2," + 
							"X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR," +
							"X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM" +
							") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = conn.prepareStatement(sql);
			
			int dataLength = wifiInfo.getTbPublicWifiInfo().getRow().size();
			
			for (int i=0; i<dataLength; i++) {
				preparedStatement.setString(1, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiMgrNo());
				preparedStatement.setString(2, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiWrdofc());
				preparedStatement.setString(3, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiMainNm());
				preparedStatement.setString(4, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiAdres1());
				preparedStatement.setString(5, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiAdres2());
				preparedStatement.setString(6, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiInstlFloor());
				preparedStatement.setString(7, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiInstlTy());
				preparedStatement.setString(8, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiInstlMby());
				preparedStatement.setString(9, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiSvcSe());
				preparedStatement.setString(10, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiCmcwr());
				preparedStatement.setString(11, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiCnstcYear());
				preparedStatement.setString(12, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiInoutDoor());
				preparedStatement.setString(13, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getXSwifiRemars3());
				preparedStatement.setString(14, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getLat());
				preparedStatement.setString(15, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getLnt());
				preparedStatement.setString(16, wifiInfo.getTbPublicWifiInfo().getRow().get(i).getWorkDttm());
				
				int executeResult = preparedStatement.executeUpdate();				
				if (executeResult > 0) {
					result++;
				} else {
					break;
				}
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		return result;
	}
	
	/*
	 * Open API 와이파이 정보 가져오기 히스토리 저장
	 * */
	public void myLocHistoryInsert(String lat, String lnt) {
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
				
		try {
			conn = getDBConnection();
			
			String sql = "INSERT INTO USER_LOC_HISTORY (LAT, LNT) VALUES (?, ?)";
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, lat);
			preparedStatement.setString(2, lnt);
		
			preparedStatement.executeUpdate();				
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
	}
	
	/*
	 * 내 위치 히스토리 목록 조회
	 * */
	public List<MyLocHistory> selectMyLocHisList() {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		List<MyLocHistory> myLocHisList = new ArrayList<>();
		
		try {
			conn = getDBConnection();
			
			String sql = "SELECT * FROM USER_LOC_HISTORY WHERE DELETE_YN = 'N'";
			preparedStatement = conn.prepareStatement(sql);	
			
			rs = preparedStatement.executeQuery();
			
	        while (rs.next()) {
	        	MyLocHistory history = new MyLocHistory();
	        	history.setId(rs.getString("ID"));
	        	history.setLat(rs.getString("LAT"));
	        	history.setLnt(rs.getString("LNT"));
	        	history.setUseDttm(rs.getString("USE_DTTM"));
	        	myLocHisList.add(history);
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		return myLocHisList;
	}

	
}
