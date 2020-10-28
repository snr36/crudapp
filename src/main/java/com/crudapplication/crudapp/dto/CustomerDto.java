package com.crudapplication.crudapp.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class CustomerDto {

    @Id
    private Integer id; 
    private String name;
    private Integer tier;
    
}
