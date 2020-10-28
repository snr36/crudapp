package com.crudapplication.crudapp.repository;

import com.crudapplication.crudapp.repository.dao.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    
}
