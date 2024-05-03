package com.demo.backend.business;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.backend.entity.User;
import com.demo.backend.exception.BaseException;
import com.demo.backend.exception.UserException;
import com.demo.backend.mapper.UserMapper;
import com.demo.backend.model.MLoginRequest;
import com.demo.backend.model.MRegisterRequest;
import com.demo.backend.model.MRegisterResponse;
import com.demo.backend.service.TokenService;
import com.demo.backend.service.UserService;
import com.demo.backend.util.SecurityUtil;

@Service
public class UserBusiness {

    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());
        return userMapper.toRegisterResponse(user);
    }

    public String login(MLoginRequest request) throws BaseException {
        // Validate

        // Verify Database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if (opt.isEmpty()) {
            // throw login fail
            throw UserException.loginFailEmailNotfound();
        }
        User user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            // throw login fail
            throw UserException.loginFailPasswordIncorrert();
        }

        return tokenService.tokennize(user);

    }

    public String refreshToken() throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }
        String userId = opt.get();
        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }
        User user = optUser.get();
        return tokenService.tokennize(user);
    }
}
