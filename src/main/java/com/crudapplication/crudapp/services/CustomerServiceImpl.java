package com.crudapplication.crudapp.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.crudapplication.crudapp.controller.Requests.CustomerRequest;
import com.crudapplication.crudapp.dto.CustomerDto;
import com.crudapplication.crudapp.repository.CustomerRepository;
import com.crudapplication.crudapp.repository.dao.Customer;
import com.crudapplication.crudapp.utility.logs.LogData;

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
        log.trace("Converting to Dto");
        return new ModelMapper().map(customer, CustomerDto.class);
    }
   
    public List<CustomerDto> getCustomers(){ 
        log.debug("In getCustomers method service layer");
        List<Customer> customer = customerrepository.findAll();
        log.debug("Retriving all customers from database");
        LogData<Customer> logData = new LogData<>(customer);
        log.debug("Retrieved customers: {}",logData);
        log.debug("retrived all customers from database");
        return customer.stream().map(this :: convertToDto).collect(Collectors.toList());
    }  

    public CustomerDto getCustomerById(Integer customerId) {
        log.debug("In getCustomerById method service layer");
        Customer customerDao = customerrepository.findById(customerId).orElse(null);
        log.debug("Retriving customer with id {} from database",customerId);
        LogData<Customer> logData = new LogData<>(Arrays.asList(customerDao));
        log.debug("Retrieved customer: {}",logData) ;
        log.trace("Retrived customer with id {} and sending to controller",customerId);
        return customerDao != null ? convertToDto(customerDao) : null ;
    }

    public CustomerDto addCustomer(CustomerRequest customerRequest){
        log.debug("In addCustomer method service layer");
        Customer customerDao = new Customer(customerRequest.getId(),customerRequest.getName(),customerRequest.getTier());
        log.debug("Adding customer {} to database",customerDao);
        customerrepository.save(customerDao);
        log.debug("customer with id {} is added successfully",customerDao.getId());
        return convertToDto(customerDao);
    }

    public CustomerDto updateCustomer(CustomerRequest customerRequest){
        log.debug("In updateCustomer method service layer");
        Customer customerDao = new Customer(customerRequest.getId(),customerRequest.getName(),customerRequest.getTier());
        log.debug("Updating customer {} to database",customerDao);
        customerrepository.save(customerDao); 
        log.debug("Customer with id {} is updated successfully",customerDao.getId());
        return convertToDto(customerDao);  
    }

    public void deleteCustomer(Integer customerId) {
        log.debug("In deleteCustomer method service layer");
        log.debug("Deleting customer with id {} from database",customerId);
        customerrepository.delete(customerrepository.getOne(customerId));
        log.debug("Customer is deleted successfully");
    }
}
  