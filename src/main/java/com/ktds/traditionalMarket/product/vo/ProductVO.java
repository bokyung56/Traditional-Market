package com.ktds.traditionalmarket.product.vo;

public class ProductVO {

	private String productId;			
	private String storeId;
	private String largeClassification;		// 대분류
	private String mediumClassification;	// 중분류
	private String smallClassification;		// 소분류
	private String name;					// 상품이름
	private int price;						// 가격
	private String picture;					// 사진

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLargeClassification() {
		return largeClassification;
	}

	public void setLargeClassification(String largeClassification) {
		this.largeClassification = largeClassification;
	}

	public String getMediumClassification() {
		return mediumClassification;
	}

	public void setMediumClassification(String mediumClassification) {
		this.mediumClassification = mediumClassification;
	}

	public String getSmallClassification() {
		return smallClassification;
	}

	public void setSmallClassification(String smallClassification) {
		this.smallClassification = smallClassification;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
