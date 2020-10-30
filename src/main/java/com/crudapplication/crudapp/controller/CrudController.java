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
import com.crudapplication.crudapp.utility.logs.LogData;

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
        log.debug("Accessing home method");
        return "Welcome to crudapp";
    }
 
    @GetMapping("/customers") 
    public ResponseEntity<Container<CustomerDto>> getCustomers(){
        log.debug("Accessing getCustomers method in controller");
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(customerservice.getCustomers())); 
    } 
 
    @GetMapping("/products")
    public ResponseEntity<Container<ProductDto>> getProducts() {
        Container<ProductDto> product = new Container<>(productservice.getProducts());
        log.debug("Accessing getProducts method in controller");
        if(product.size() <= 0){
            log.error("No products found.List size is 0");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.debug("retriving all products");   
        log.info("All products retrieved");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }


    @GetMapping("customers/{customerId}")
    public ResponseEntity<Container<CustomerDto>> getCustomerById(@PathVariable("customerId") Integer customerId) {
        log.debug("In getCustomerById method in controller");
        CustomerDto customerDto = customerservice.getCustomerById(customerId);

        if(customerDto != null) {
            log.debug("Getting customer with id {}",customerId);
            log.info("Customer with id {} is found",customerId);  
            return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(customerDto)));
        }
        else{
            log.error("Customer with id {} is not found",customerId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } 
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Container<ProductDto>> getProductById(@PathVariable Integer productId) {
        log.debug("In getProductById method in controller");
        ProductDto productDto = productservice.getProductById(productId);

        if(productDto == null){
            log.error("Product with given id {} is not found", productId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.debug("Getting product with id {} ",productId);
        log.info("Product with given id {} is found",productId);
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(productDto)));
    }

 
    @PostMapping("/customers")  
    public ResponseEntity<Container<CustomerDto>> addCustomer(@Valid @RequestBody CustomerRequest customer) {
        log.debug("In addCustomer method in controller");
        CustomerDto customerDto = customerservice.addCustomer(customer);
        log.debug("Adding customer with id {} ",customerDto.getId());
        log.info("Customer with id {} is added successfully",customerDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(customerDto)));
    
    }

    @PostMapping("/products")
    public ResponseEntity<Container<ProductDto>> addProduct(@Valid @RequestBody ProductRequest product) {
        log.debug("In addProduct method in controller");
        ProductDto productDto = productservice.addProduct(product);
        log.info("Product with id {} is added successfully",productDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(productDto)));
    }

    @PutMapping("/customers")
    public ResponseEntity<Container<CustomerDto>> updateCustomer(@Valid @RequestBody CustomerRequest customer) {
        log.debug("In updateCustomer method in controller");
        CustomerDto customerDto = customerservice.updateCustomer(customer);
        log.info("Customer with id {} is updated succesfully",customerDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(customerDto)));
    }

    @PutMapping("/products")
    public ResponseEntity<Container<ProductDto>> updateProduct(@Valid @RequestBody ProductRequest product) {
        log.debug("In updateProduct method in controller");
        ProductDto productDto = productservice.updateProduct(product);
        log.info("Product with id {} is updated successfully",productDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new Container<>(Arrays.asList(productDto)));
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) {
        log.debug("In deleteCustomer method in controller");
        customerservice.deleteCustomer(customerId);
        log.info("Customer with id {} is deleted successfully",customerId);
        return ResponseEntity.status(HttpStatus.OK).build(); 
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        log.debug("In deleteProduct method in controller");
        productservice.deleteProduct(productId);
        log.info("Product with id {} deleted successfully",productId);
        return ResponseEntity.status(HttpStatus.OK).build(); 
    } 


} 
