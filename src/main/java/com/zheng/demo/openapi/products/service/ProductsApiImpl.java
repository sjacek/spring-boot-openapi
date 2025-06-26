package com.zheng.demo.openapi.products.service;

import java.math.BigDecimal;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.zheng.demo.openapi.products.api.ProductsApiDelegate;
import com.zheng.demo.openapi.products.api.model.ProductDO;

@Component
public class ProductsApiImpl implements ProductsApiDelegate {
	Logger logger = LoggerFactory.getLogger(ProductsApiImpl.class);
	private final Random rnd = new Random();

	@Override
	public ResponseEntity<ProductDO> getProduct(BigDecimal id) {
		logger.info("getProduct called");
		ProductDO prod = new ProductDO();
		prod.setId(id);
		prod.setName("Product_" + id);
		prod.setPrice(BigDecimal.valueOf(100.0 + rnd.nextDouble() * 100.0));

		return ResponseEntity.ok(prod);
	}

	@Override
	public ResponseEntity<ProductDO> createProduct(ProductDO product) {
		logger.info("createProduct called");
		product.setId(BigDecimal.valueOf(rnd.nextDouble() * 10));
		return ResponseEntity.ok(product);

	}
}
