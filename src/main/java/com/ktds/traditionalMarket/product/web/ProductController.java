package com.ktds.traditionalmarket.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ktds.traditionalmarket.product.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;

}
