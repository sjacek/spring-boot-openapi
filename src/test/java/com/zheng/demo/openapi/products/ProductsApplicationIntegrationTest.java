package com.zheng.demo.openapi.products;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zheng.demo.openapi.products.api.model.ProductDO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsApplicationIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void whenGetProduct_thenSuccess() {
		ResponseEntity<ProductDO> response = restTemplate.getForEntity("http://localhost:" + port + "/v1/products/1",
				ProductDO.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void whenGetProductMultipleTimes_thenResponseCached() {

		// Call server a few times and collect responses
		var quotes = IntStream.range(1, 10).boxed()
				.map((i) -> restTemplate.getForEntity("http://localhost:" + port + "/v1/products/1", ProductDO.class))
				.map(HttpEntity::getBody).collect(Collectors.groupingBy((q -> {
                    Assertions.assertNotNull(q);
                    return q.hashCode();
                }), Collectors.counting()));

		assertThat(quotes.size()).isEqualTo(1);
	}

	@Test
	void whenCreateProduct_thenSuccess() {
		ProductDO product = new ProductDO();
		product.setName("TEST");
		ResponseEntity<ProductDO> response = restTemplate.postForEntity("http://localhost:" + port + "/v1/products",
				product, ProductDO.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}