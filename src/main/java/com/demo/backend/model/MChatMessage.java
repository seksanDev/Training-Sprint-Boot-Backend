package com.demo.backend.model;

import java.util.Date;

import lombok.Data;

@Data
public class MChatMessage {
    private String from;
    private String message;
    private Date created;

    public MChatMessage() {
        created = new Date();
    }
}
