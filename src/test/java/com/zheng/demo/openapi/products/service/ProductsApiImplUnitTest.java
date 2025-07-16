package com.zheng.demo.openapi.products.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.zheng.demo.openapi.products.api.v1.ProductsApi;
import com.zheng.demo.openapi.products.api.v1.model.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ProductsApiImplUnitTest {

	@Autowired
	private ProductsApi api;

	@Test
	void whenGetProduct_then_success() {

		ResponseEntity<ProductDTO> response = api.getProduct(new BigDecimal(1));
		assertThat(response).isNotNull();

		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

	}

	@Test
	void whenCreateProduct_then_success() {
		ProductDTO product = new ProductDTO();
		product.setName("Test");
		product.setPrice(new BigDecimal(100));
		ResponseEntity<ProductDTO> response = api.createProduct(product);
		assertThat(response).isNotNull();

		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

	}

}