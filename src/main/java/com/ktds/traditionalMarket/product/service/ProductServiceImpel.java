package com.ktds.traditionalmarket.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.product.biz.ProductBiz;


@Service
public class ProductServiceImpel implements ProductService{

	@Autowired
	private ProductBiz productBiz;
	

}
