package com.crudapplication.crudapp.services;

import java.util.List;

import com.crudapplication.crudapp.controller.Requests.CustomerRequest;
import com.crudapplication.crudapp.dto.CustomerDto;
//import com.crudapplication.crudapp.repository.dao.Customer;

public interface CustomerService {

    public List<CustomerDto> getCustomers();
    public CustomerDto getCustomerById(Integer customerId);
    public CustomerDto addCustomer(CustomerRequest customerRequest);
    public CustomerDto updateCustomer(CustomerRequest customerRequest); 
    public void deleteCustomer(Integer customerId);
}
