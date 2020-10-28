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

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired  
    private CustomerRepository customerrepository;

    public CustomerDto convertToDto(Customer customer) {
        return new ModelMapper().map(customer, CustomerDto.class);
    }
   
    public List<CustomerDto> getCustomers(){ 
        return customerrepository.findAll().stream().map(this :: convertToDto).collect(Collectors.toList());
    }  

    public CustomerDto getCustomerById(Integer customerId) {
        Customer customerDao = customerrepository.findById(customerId).orElse(null);
        return customerDao != null ? convertToDto(customerDao) : null ;
    }

    public CustomerDto addCustomer(CustomerRequest customerRequest){
        Customer customerDao = new Customer(customerRequest.getId(),customerRequest.getName(),customerRequest.getTier());
        customerrepository.save(customerDao);
        return convertToDto(customerDao);
    }

    public CustomerDto updateCustomer(CustomerRequest customerRequest){
        Customer customerDao = new Customer(customerRequest.getId(),customerRequest.getName(),customerRequest.getTier());
        customerrepository.save(customerDao); 
        return convertToDto(customerDao);  
    }

    public void deleteCustomer(Integer customerId) {
        customerrepository.delete(customerrepository.getOne(customerId));
    }
}
  