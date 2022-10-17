package com.lotte.danuri.order.client;

import com.lotte.danuri.order.model.dto.client.ProductDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Product")
//@FeignClient(value = "Product", url = "http://127.0.0.1:8080")
public interface ProductServiceClient {

    @GetMapping(value = "/products/{productId}")
    ProductDto getProduct(@PathVariable Long productId);
}
