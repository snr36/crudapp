package com.crudapplication.crudapp.controller;

import java.util.Arrays;
//import java.util.List;

import javax.validation.Valid;


import com.crudapplication.crudapp.controller.Requests.CustomerRequest;
import com.crudapplication.crudapp.controller.Requests.ProductRequest;
import com.crudapplication.crudapp.dto.CustomerDto;
import com.crudapplication.crudapp.dto.ProductDto;
import com.crudapplication.crudapp.repository.dao.Container;
import com.crudapplication.crudapp.services.CustomerService;
import com.crudapplication.crudapp.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

  
//import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class CrudController {

    @Autowired 
    private CustomerService customerservice;

    @Autowired
    private ProductService productservice;
    

    @GetMapping("/home")
    public String home(){
        log.trace("A TRACE Message");
        return "Welcome to crudapp";
    }

    @GetMapping("/customers")
    public ResponseEntity<Container<CustomerDto>> getCustomers(){
        log.trace("In getCustomers");
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(customerservice.getCustomers()));
    } 
 
    @GetMapping("/products")
    public ResponseEntity<Container<ProductDto>> getProducts() {

       Container<ProductDto> product = new Container<>(productservice.getProducts());

        if(product.size() <= 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }


    @GetMapping("customers/{customerId}")
    public ResponseEntity<Container<CustomerDto>> getCustomerById(@PathVariable("customerId") Integer customerId) {

        CustomerDto customerdto = customerservice.getCustomerById(customerId);

        if(customerdto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(customerdto)));
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Container<ProductDto>> getProductById(@PathVariable Integer productId) {

        ProductDto productdto = productservice.getProductById(productId);

        if(productdto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(productdto)));
    }

 
    @PostMapping("/customers")
    public ResponseEntity<Container<CustomerDto>> addCustomer(@Valid @RequestBody CustomerRequest customer) {
        CustomerDto customerdto = customerservice.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(customerdto)));
    
    }

    @PostMapping("/products")
    public ResponseEntity<Container<ProductDto>> addProduct(@Valid @RequestBody ProductRequest product) {
        ProductDto productdto = productservice.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(productdto)));
    }

    @PutMapping("/customers")
    public ResponseEntity<Container<CustomerDto>> updateCustomer(@Valid @RequestBody CustomerRequest customer) {
        CustomerDto customerdto = customerservice.updateCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(customerdto)));
    }

    @PutMapping("/products")
    public ResponseEntity<Container<ProductDto>> updateProduct(@Valid @RequestBody ProductRequest product) {
        ProductDto productdto = productservice.updateProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(productdto)));
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) {
        try {
            customerservice.deleteCustomer(customerId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        } 
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        try {
            productservice.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        } 
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }


} 
