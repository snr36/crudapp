package com.crudapplication.crudapp.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class ProductDto {
    
    @Id
    private Integer id;
    private String pname;
    private Integer cost;
}
