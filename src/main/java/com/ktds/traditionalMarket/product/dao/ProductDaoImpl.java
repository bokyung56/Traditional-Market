package com.ktds.traditionalmarket.product.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.traditionalmarket.product.vo.ProductVO;

@Repository
public class ProductDaoImpl extends SqlSessionDaoSupport implements ProductDao{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	// 하나의 Store에서 여러개의 Products가져오기
	@Override
	public List<ProductVO> selectAllProducts(String storeId) {
		
		return getSqlSession().selectList("ProductDao.selectAllProducts", storeId);
	}

}
