package com.crudapplication.crudapp.services;

import java.util.List;
import java.util.stream.Collectors;

import com.crudapplication.crudapp.controller.Requests.ProductRequest;
import com.crudapplication.crudapp.dto.ProductDto;
import com.crudapplication.crudapp.repository.ProductRepository;
import com.crudapplication.crudapp.repository.dao.Product;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productrepository;

    private ProductDto convertToDto(Product product) {
        return new ModelMapper().map(product,ProductDto.class);
    }

    public List<ProductDto> getProducts(){
        List<Product> product = productrepository.findAll();
        return product.stream().map(this :: convertToDto).collect(Collectors.toList());
    }
 
    public ProductDto getProductById(Integer productId){
        Product productDao = productrepository.findById(productId).orElse(null);
        return productDao != null ? convertToDto(productDao) : null ;
    }

    public ProductDto addProduct(ProductRequest productRequest){
        Product productDao = new Product(productRequest.getId(),productRequest.getPname(),productRequest.getCost());
        productrepository.save(productDao);
        return convertToDto(productDao);
    }

    public ProductDto updateProduct(ProductRequest productRequest){
        Product productDao = new Product(productRequest.getId(),productRequest.getPname(),productRequest.getCost());
        productrepository.save(productDao);
        return convertToDto(productDao);
    } 

    public void deleteProduct(Integer productId) {
        productrepository.delete(productrepository.getOne(productId));
    }
    
} 
