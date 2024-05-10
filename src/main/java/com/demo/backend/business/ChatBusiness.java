package com.demo.backend.business;

import java.util.Optional;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.demo.backend.exception.BaseException;
import com.demo.backend.exception.ChatException;
import com.demo.backend.model.MChatMessage;
import com.demo.backend.model.MChatMessageRequest;
import com.demo.backend.util.SecurityUtil;

@Service
public class ChatBusiness {
    private final SimpMessagingTemplate template;

    public ChatBusiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post(MChatMessageRequest request) throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw ChatException.accessDenied();
        }
        // TODO: validate message
        final String destination = "/topic/chat";
        MChatMessage payload = new MChatMessage();
        payload.setFrom(opt.get());
        payload.setMessage(request.getMessage());
        template.convertAndSend(destination, payload);
    }
}
