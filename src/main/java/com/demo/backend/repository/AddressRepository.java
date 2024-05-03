package com.demo.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.backend.entity.Address;
import com.demo.backend.entity.User;

public interface AddressRepository extends CrudRepository<Address, String> {
    List<Address> findByUser(User user);
}
