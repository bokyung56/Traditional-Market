package com.ktds.traditionalmarket.store.vo;

import java.util.List;

import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;
import com.ktds.traditionalmarket.product.vo.ProductVO;
import com.ktds.traditionalmarket.store.reply.vo.StoreReplyVO;

public class StoreVO {
	private String storeId;
	private String marketId;
	private String storeName;
	private String representative;
	private String storeInformation;
	private String phone;
	private String latitude;
	private String longitude;
	private String picture;
	
	private List<StoreReplyVO> storeReplyList;	// 하나의 상점에 대한 댓글들	
	private double storeRating; 				// 10.18.목 하나의 상점에 대한 총 평점	
	private List<ProductVO> productList;		// 10.18.목 하나의 상점에 대한 상품들

	
	public List<ProductVO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductVO> productList) {
		this.productList = productList;
	}

	public double getStoreRating() {
		return storeRating;
	}

	public void setStoreRating(double storeRating) {
		this.storeRating = storeRating;
	}

	// getter, setter
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getStoreInformation() {
		return storeInformation;
	}

	public void setStoreInformation(String storeInformation) {
		this.storeInformation = storeInformation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<StoreReplyVO> getStoreReplyList() {
		return storeReplyList;
	}

	public void setStoreReplyList(List<StoreReplyVO> storeReplyList) {
		this.storeReplyList = storeReplyList;
	}


	

}
