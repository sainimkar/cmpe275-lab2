package com.cmpe275.Lab2.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;

//    public void setStreet(String street){
//        this.street = street;
//    }
//
//    public String getStreet(String street){
//        return this.street;
//    }
//
//    public void setCity(String city){
//        this.city = city;
//    }
//
//    public String getCity(String city){
//        return this.city;
//    }
//
//    public void setState(String state){
//        this.state = state;
//    }
//
//    public String getState(String state){
//        return this.state;
//    }
//
//    public void setZip(String zip){
//        this.zip = zip;
//    }
//
//    public String getZip(String zip){
//        return this.zip;
//    }

}
