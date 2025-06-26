package com.zheng.demo.openapi.products.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.zheng.demo.openapi.products.api.ProductsApi;
import com.zheng.demo.openapi.products.api.model.ProductDO;

@SpringBootTest
class ProductsApiImplUnitTest {

	@Autowired
	private ProductsApi api;

	@Test
	void whenGetProduct_then_success() {

		ResponseEntity<ProductDO> response = api.getProduct(new BigDecimal(1));
		assertThat(response).isNotNull();

		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

	}

	@Test
	void whenCreateProduct_then_success() {
		ProductDO product = new ProductDO();
		product.setName("Test");
		product.setPrice(new BigDecimal(100));
		ResponseEntity<ProductDO> response = api.createProduct(product);
		assertThat(response).isNotNull();

		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

	}

}