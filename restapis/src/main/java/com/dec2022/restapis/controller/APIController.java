package com.dec2022.restapis.controller;

import com.dec2022.restapis.model.AddProductRequest;
import com.dec2022.restapis.model.Product;
import com.dec2022.restapis.model.PutProductRequest;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/")
public class APIController {
    //Temporary storage for product data. In long term data should be in MySQL database.
    private Map<String, Product> productMap = new HashMap<>();

    //Identify the resource? Product

    //@RequestMapping(value = "list-products", method = RequestMethod.GET)
    //GET, POST, PUT, DELETE, PATCH
    @GetMapping(value = "product")
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = new ArrayList<>();
        for (String key : productMap.keySet()) {
            products.add(productMap.get(key));
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "product")
    public ResponseEntity<Product> addProduct(@RequestBody AddProductRequest addProductRequest) {
        String productId = UUID.randomUUID().toString();
        Product product = new Product(productId, addProductRequest.getProductName(), addProductRequest.getPrice());
        productMap.put(productId, product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "product")
    public ResponseEntity<String> deleteProduct(@RequestParam String productId, @RequestParam String productName) {
        if (productId != null && productId.length() > 0 && productMap.containsKey(productId)) {
            productMap.remove(productId);
            return new ResponseEntity<>("Product with id " + productId + " deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid product id.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "product")
    public ResponseEntity<Product> updateProduct(@RequestBody PutProductRequest putProductRequest) {
        if (putProductRequest.getProductId() == null || !productMap.containsKey(putProductRequest.getProductId())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Product product = productMap.get(putProductRequest.getProductId());
        product.setProductName(putProductRequest.getProductName());
        product.setPrice(putProductRequest.getPrice());

        productMap.put(putProductRequest.getProductId(), product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PatchMapping(value = "product")
    public ResponseEntity<Product> patchProduct(@RequestBody PutProductRequest putProductRequest) {
        if (putProductRequest.getProductId() == null || !productMap.containsKey(putProductRequest.getProductId())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Product product = productMap.get(putProductRequest.getProductId());
        if (putProductRequest.getProductName() != null) {
            product.setProductName(putProductRequest.getProductName());
        }

        if (putProductRequest.getPrice() != null) {
            product.setPrice(putProductRequest.getPrice());
        }
        productMap.put(putProductRequest.getProductId(), product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/v1/delete-product-1/{productId}/{productName}")
    public ResponseEntity<String> deleteProduct1(@PathVariable String productId, @PathVariable String productName) {
        if (productId != null && productId.length() > 0 && productMap.containsKey(productId)) {
            productMap.remove(productId);
            return new ResponseEntity<>("Product with id " + productId + " deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid product id.", HttpStatus.BAD_REQUEST);
    }
}
