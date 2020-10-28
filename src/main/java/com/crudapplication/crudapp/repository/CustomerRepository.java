package com.crudapplication.crudapp.repository;

import com.crudapplication.crudapp.repository.dao.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    
}
