package com.crudapplication.crudapp.controller.Requests;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//import lombok.Data;


public class CustomerRequest {
    
    @Id
    private Integer id; 

    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    @Max(3)
    private Integer tier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }
}
