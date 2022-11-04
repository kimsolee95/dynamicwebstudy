package dynamicwebpractice.test.openapi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	
	public static void main(String[] args) throws IOException {

		
		ApiExplorer apiExplorer = new ApiExplorer();
		WifiInfo wifiInfo = apiExplorer.callWifiInfoApi();
		
		DBUtil dBUtil = new DBUtil();
		dBUtil.dbInsert(wifiInfo);
		
	}
	
	public void dbSelect() {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		String dbFile = "C:\\dynamicwebpractice\\test1.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			String sql = "SELECT * FROM PUBLIC_WIFY_INFO";
			preparedStatement = conn.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			
	        while (rs.next()) {
	            String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
	            String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
	            String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");

	            System.out.println(X_SWIFI_MGR_NO + ", " + X_SWIFI_WRDOFC + "," + X_SWIFI_MAIN_NM);
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
				
	}
	
	
	
	
	public void dbInsert(WifiInfo wifiInfo) {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		String dbFile = "C:\\dynamicwebpractice\\test1.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			String sql = "insert into PUBLIC_WIFY_INFO (" +
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
					System.out.println("save seccess!!");
				} else {
					System.out.println("save fail!!");
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
				
	}
	
	
	
}
