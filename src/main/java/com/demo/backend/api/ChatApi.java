package com.demo.backend.api;

import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.business.ChatBusiness;
import com.demo.backend.exception.BaseException;
import com.demo.backend.model.MChatMessageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/chat")
public class ChatApi {
    private final ChatBusiness business;

    public ChatApi(ChatBusiness business) {
        this.business = business;
    }

    @PostMapping("/message")
    public ResponseEntity<Void> post(@RequestBody MChatMessageRequest request) throws BaseException {
        business.post(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
