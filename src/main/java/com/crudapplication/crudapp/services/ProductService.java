package com.crudapplication.crudapp.services;

import java.util.List;

import com.crudapplication.crudapp.controller.Requests.ProductRequest;
import com.crudapplication.crudapp.dto.ProductDto;

public interface ProductService {

    public List<ProductDto> getProducts();
    public ProductDto getProductById(Integer productId);
    public ProductDto addProduct(ProductRequest productRequest);
    public ProductDto updateProduct(ProductRequest productRequest); 
    public void deleteProduct(Integer productId);
}
