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
        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = new CustomerDto();
        customerDto = modelMapper.map(customer, CustomerDto.class);
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setTier(customer.getTier());
        return customerDto;
    }
   
    
    public List<CustomerDto> getCustomers(){
        List<Customer> customers = customerrepository.findAll();
        List<CustomerDto> customerDto = customers.stream().map(this :: convertToDto).collect(Collectors.toList());
        return customerDto;
    }  

    public CustomerDto getCustomerById(Integer customerId) {
        Customer customerDao = null;
        customerDao = customerrepository.findById(customerId).orElse(null);
        CustomerDto customerDto ;

        if(customerDao != null){
            customerDto = convertToDto(customerDao);
        }
        else{
            customerDto = null;
        } 
        return customerDto; 
    }

    public CustomerDto addCustomer(CustomerRequest customerRequest){

        Customer customerDao = new Customer();
        customerDao.setId(customerRequest.getId());
        customerDao.setName(customerRequest.getName());
        customerDao.setTier(customerRequest.getTier()); 
        customerrepository.save(customerDao);

        CustomerDto customerDto = convertToDto(customerDao);


        return customerDto;
    }

    public CustomerDto updateCustomer(CustomerRequest customerRequest){
        
        Customer customerDao = new Customer();
        customerDao.setId(customerRequest.getId());
        customerDao.setName(customerRequest.getName());
        customerDao.setTier(customerRequest.getTier()); 
        customerrepository.save(customerDao); 
        CustomerDto customerDto = convertToDto(customerDao); 

        return customerDto;
    }

    public void deleteCustomer(Integer customerId) {
        Customer customer = customerrepository.getOne(customerId);
        customerrepository.delete(customer);
    }
}
