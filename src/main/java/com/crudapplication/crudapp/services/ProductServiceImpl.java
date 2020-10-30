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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productrepository;

    private ProductDto convertToDto(Product product) {
        log.trace("Converting to dto...");
        return new ModelMapper().map(product,ProductDto.class);
    }

    public List<ProductDto> getProducts(){
        log.debug("In getProducts method service layer");
        List<Product> product = productrepository.findAll();
        log.debug("Retriving all products from database");
        log.debug("retrived all products from database ");
        return product.stream().map(this :: convertToDto).collect(Collectors.toList()); 
    }
 
    public ProductDto getProductById(Integer productId){
        log.debug("In getProductById method service layer");
        Product productDao = productrepository.findById(productId).orElse(null);
        log.debug("Retriving product with id {} from database",productId);
        log.trace("Retrived product with id {} and sending to controller",productId);
        return productDao != null ? convertToDto(productDao) : null ;
    }

    public ProductDto addProduct(ProductRequest productRequest){
        log.debug("In addProduct method service layer");
        Product productDao = new Product(productRequest.getId(),productRequest.getPname(),productRequest.getCost());
        log.debug("Adding product {} to database",productDao);
        productrepository.save(productDao);
        log.debug("Product with id {} is added successfully",productDao.getId());
        return convertToDto(productDao);
    }

    public ProductDto updateProduct(ProductRequest productRequest){
        log.debug("In updateProduct method service layer");
        Product productDao = new Product(productRequest.getId(),productRequest.getPname(),productRequest.getCost());
        log.debug("Updating product {} to database",productDao);
        productrepository.save(productDao);
        log.debug("Product with id {} is updated successfully",productDao.getId());
        return convertToDto(productDao);
    } 

    public void deleteProduct(Integer productId) {
        log.debug("In deleteProduct method service layer");
        log.debug("Deleting product with id {} from database",productId);
        productrepository.delete(productrepository.getOne(productId));
        log.debug("Product is deleted successfully");
    }
    
} 
