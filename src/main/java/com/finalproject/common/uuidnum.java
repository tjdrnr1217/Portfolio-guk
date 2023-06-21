package com.finalproject.common;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class uuidnum {
    
    public String uuidString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
