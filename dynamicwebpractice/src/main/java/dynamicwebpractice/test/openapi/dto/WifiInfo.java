package dynamicwebpractice.test.openapi.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import dynamicwebpractice.test.openapi.dto.TbPublicWifiInfo;

public class WifiInfo {

	@SerializedName("TbPublicWifiInfo")
	@Expose
	private TbPublicWifiInfo tbPublicWifiInfo;

	public TbPublicWifiInfo getTbPublicWifiInfo() {
	return tbPublicWifiInfo;
	}

	public void setTbPublicWifiInfo(TbPublicWifiInfo tbPublicWifiInfo) {
	this.tbPublicWifiInfo = tbPublicWifiInfo;
	}
	
	
}