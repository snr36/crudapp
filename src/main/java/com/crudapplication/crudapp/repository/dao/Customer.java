package com.crudapplication.crudapp.repository.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
public class Customer  {

    @Id
    @NotNull
    private Integer id; 

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    @Max(3)
    private Integer tier;

    public Customer() {
        
    }

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

    
   // @Override
    //public String toString() {
     //   return "Customer = [id "+ id + ",name = "+ name + ",tier = "+ tier +" ";
    //}

}
