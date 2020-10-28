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
        ProductDto productDto = new ProductDto();
        ModelMapper modelMapper = new ModelMapper();
        productDto = modelMapper.map(product,ProductDto.class);
        productDto.setId(product.getId());
        productDto.setPname(product.getPname());
        productDto.setCost(product.getCost());
        
        return productDto;
    }


    public List<ProductDto> getProducts(){
        List<Product> products = productrepository.findAll();
        List<ProductDto> productDto= products.stream().map(this :: convertToDto).collect(Collectors.toList());

        return productDto;
    }
 
    public ProductDto getProductById(Integer productId){
        Product productDao = null;
        productDao = productrepository.findById(productId).orElse(null);

        ProductDto productDto ;
        if(productDao != null){
           productDto = convertToDto(productDao);
        }
        else {
            productDto = null;
        }
        return productDto;
    }

    public ProductDto addProduct(ProductRequest productRequest){

        Product productDao = new Product();
        productDao.setId(productRequest.getId());
        productDao.setPname(productRequest.getPname());
        productDao.setCost(productRequest.getCost());
        productrepository.save(productDao);

        ProductDto productDto = convertToDto(productDao);

        return productDto;
    }

    public ProductDto updateProduct(ProductRequest productRequest){

        Product productDao = new Product();
        productDao.setId(productRequest.getId());
        productDao.setPname(productRequest.getPname());
        productDao.setCost(productRequest.getCost());
        productrepository.save(productDao);
        
        ProductDto productDto = convertToDto(productDao);

        return productDto;
    } 

    public void deleteProduct(Integer productId) {
        Product productDao = productrepository.getOne(productId);
        productrepository.delete(productDao);
    }
    
} 
