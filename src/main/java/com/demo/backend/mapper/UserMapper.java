package com.demo.backend.mapper;

import org.mapstruct.Mapper;

import com.demo.backend.entity.User;
import com.demo.backend.model.MRegisterResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    MRegisterResponse toRegisterResponse(User user);
}