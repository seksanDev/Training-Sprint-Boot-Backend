package com.demo.backend.model;

import lombok.Data;

@Data
public class MLoginRequest {
    private String email;
    private String password;
}
