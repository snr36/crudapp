package com.crudapplication.crudapp.services;

import java.util.List;
import java.util.stream.Collectors;

import com.crudapplication.crudapp.controller.Requests.CustomerRequest;
import com.crudapplication.crudapp.dto.CustomerDto;
import com.crudapplication.crudapp.repository.CustomerRepository;
import com.crudapplication.crudapp.repository.dao.Customer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired  
    private CustomerRepository customerrepository;

    public CustomerDto convertToDto(Customer customer) {
        log.debug("Converting to Dto");
        return new ModelMapper().map(customer, CustomerDto.class);
    }
   
    public List<CustomerDto> getCustomers(){ 
        log.debug("In getCustomers method service layer");
        return customerrepository.findAll().stream().map(this :: convertToDto).collect(Collectors.toList());
    }  

    public CustomerDto getCustomerById(Integer customerId) {
        log.debug("In getCustomerById method service layer");
        Customer customerDao = customerrepository.findById(customerId).orElse(null);
        return customerDao != null ? convertToDto(customerDao) : null ;
    }

    public CustomerDto addCustomer(CustomerRequest customerRequest){
        log.debug("In addCustomer method service layer");
        Customer customerDao = new Customer(customerRequest.getId(),customerRequest.getName(),customerRequest.getTier());
        customerrepository.save(customerDao);
        log.debug("customer with id {} is added successfully",customerDao.getId());
        return convertToDto(customerDao);
    }

    public CustomerDto updateCustomer(CustomerRequest customerRequest){
        log.debug("In updateCustomer method service layer");
        Customer customerDao = new Customer(customerRequest.getId(),customerRequest.getName(),customerRequest.getTier());
        customerrepository.save(customerDao); 
        log.debug("Customer with id {} is updated successfully",customerDao.getId());
        return convertToDto(customerDao);  
    }

    public void deleteCustomer(Integer customerId) {
        log.debug("In deleteCustomer method service layer");
        customerrepository.delete(customerrepository.getOne(customerId));
        log.debug("Customer is deleted successfully");
    }
}
  