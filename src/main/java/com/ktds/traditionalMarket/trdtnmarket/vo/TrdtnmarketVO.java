package com.ktds.traditionalmarket.trdtnmarket.vo;

import java.util.List;

import com.ktds.traditionalmarket.store.vo.StoreVO;

public class TrdtnmarketVO {
	private String marketId;
	private String latitude; 	// 위도
	private String longitude; 	// 경도
	private String name; 		// 재래시장 이름
	private String address; 	// 재래시장 주소
	private String page;		// 각각의 재래시장마다 jsp이름
	private String viewCount;	// 재래시장 조회수
	
	private List<StoreVO> storeList;
	

	// getter, setter
	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public List<StoreVO> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<StoreVO> storeList) {
		this.storeList = storeList;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
	
	
}
