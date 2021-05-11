package com.xander.entities;


import java.io.Serializable;

public class Payment{

    private Long id;
    private String serial;

    public static Payment newInstance() {
        Payment instance = new Payment();
        return instance;
    }
    
    public Long getId() {
        return id;
    }

    public Payment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSerial() {
        return serial;
    }

    public Payment setSerial(String serial) {
        this.serial = serial;
        return this;
    }
}

