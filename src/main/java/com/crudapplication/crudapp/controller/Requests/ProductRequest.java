package com.crudapplication.crudapp.controller.Requests;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductRequest {
    @Id 
    Integer id;

    @NotBlank
    String pname;

    @NotNull
    Integer cost;
}
