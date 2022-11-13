package dynamicwebpractice.test.openapi.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {


	@SerializedName("X_SWIFI_MGR_NO")
	@Expose
	private String xSwifiMgrNo;
	
	@SerializedName("X_SWIFI_WRDOFC")
	@Expose
	private String xSwifiWrdofc;
	
	@SerializedName("X_SWIFI_MAIN_NM")
	@Expose
	private String xSwifiMainNm;
	
	@SerializedName("X_SWIFI_ADRES1")
	@Expose
	private String xSwifiAdres1;
	
	@SerializedName("X_SWIFI_ADRES2")
	@Expose
	private String xSwifiAdres2;
	
	@SerializedName("X_SWIFI_INSTL_FLOOR")
	@Expose
	private String xSwifiInstlFloor;
	
	@SerializedName("X_SWIFI_INSTL_TY")
	@Expose
	private String xSwifiInstlTy;
	
	@SerializedName("X_SWIFI_INSTL_MBY")
	@Expose
	private String xSwifiInstlMby;
	
	@SerializedName("X_SWIFI_SVC_SE")
	@Expose
	private String xSwifiSvcSe;
	
	@SerializedName("X_SWIFI_CMCWR")
	@Expose
	private String xSwifiCmcwr;
	
	@SerializedName("X_SWIFI_CNSTC_YEAR")
	@Expose
	private String xSwifiCnstcYear;
	
	@SerializedName("X_SWIFI_INOUT_DOOR")
	@Expose
	private String xSwifiInoutDoor;
	
	@SerializedName("X_SWIFI_REMARS3")
	@Expose
	private String xSwifiRemars3;
	
	@SerializedName("LAT")
	@Expose
	private String lat;
	
	@SerializedName("LNT")
	@Expose
	private String lnt;
	
	@SerializedName("WORK_DTTM")
	@Expose
	private String workDttm;
	
	/* 추가 컬럼*/
	private Double distance;
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getXSwifiMgrNo() {
	return xSwifiMgrNo;
	}
	
	public void setXSwifiMgrNo(String xSwifiMgrNo) {
	this.xSwifiMgrNo = xSwifiMgrNo;
	}
	
	public String getXSwifiWrdofc() {
	return xSwifiWrdofc;
	}
	
	public void setXSwifiWrdofc(String xSwifiWrdofc) {
	this.xSwifiWrdofc = xSwifiWrdofc;
	}
	
	public String getXSwifiMainNm() {
	return xSwifiMainNm;
	}
	
	public void setXSwifiMainNm(String xSwifiMainNm) {
	this.xSwifiMainNm = xSwifiMainNm;
	}
	
	public String getXSwifiAdres1() {
	return xSwifiAdres1;
	}
	
	public void setXSwifiAdres1(String xSwifiAdres1) {
	this.xSwifiAdres1 = xSwifiAdres1;
	}
	
	public String getXSwifiAdres2() {
	return xSwifiAdres2;
	}
	
	public void setXSwifiAdres2(String xSwifiAdres2) {
	this.xSwifiAdres2 = xSwifiAdres2;
	}
	
	public String getXSwifiInstlFloor() {
	return xSwifiInstlFloor;
	}
	
	public void setXSwifiInstlFloor(String xSwifiInstlFloor) {
	this.xSwifiInstlFloor = xSwifiInstlFloor;
	}
	
	public String getXSwifiInstlTy() {
	return xSwifiInstlTy;
	}
	
	public void setXSwifiInstlTy(String xSwifiInstlTy) {
	this.xSwifiInstlTy = xSwifiInstlTy;
	}
	
	public String getXSwifiInstlMby() {
	return xSwifiInstlMby;
	}
	
	public void setXSwifiInstlMby(String xSwifiInstlMby) {
	this.xSwifiInstlMby = xSwifiInstlMby;
	}
	
	public String getXSwifiSvcSe() {
	return xSwifiSvcSe;
	}
	
	public void setXSwifiSvcSe(String xSwifiSvcSe) {
	this.xSwifiSvcSe = xSwifiSvcSe;
	}
	
	public String getXSwifiCmcwr() {
	return xSwifiCmcwr;
	}
	
	public void setXSwifiCmcwr(String xSwifiCmcwr) {
	this.xSwifiCmcwr = xSwifiCmcwr;
	}
	
	public String getXSwifiCnstcYear() {
	return xSwifiCnstcYear;
	}
	
	public void setXSwifiCnstcYear(String xSwifiCnstcYear) {
	this.xSwifiCnstcYear = xSwifiCnstcYear;
	}
	
	public String getXSwifiInoutDoor() {
	return xSwifiInoutDoor;
	}
	
	public void setXSwifiInoutDoor(String xSwifiInoutDoor) {
	this.xSwifiInoutDoor = xSwifiInoutDoor;
	}
	
	public String getXSwifiRemars3() {
	return xSwifiRemars3;
	}
	
	public void setXSwifiRemars3(String xSwifiRemars3) {
	this.xSwifiRemars3 = xSwifiRemars3;
	}
	
	public String getLat() {
	return lat;
	}
	
	public void setLat(String lat) {
	this.lat = lat;
	}
	
	public String getLnt() {
	return lnt;
	}
	
	public void setLnt(String lnt) {
	this.lnt = lnt;
	}
	
	public String getWorkDttm() {
	return workDttm;
	}
	
	public void setWorkDttm(String workDttm) {
	this.workDttm = workDttm;
	}
	
}
