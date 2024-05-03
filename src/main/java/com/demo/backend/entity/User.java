package com.demo.backend.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_user")
public class User extends BaseEntity {
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    // @JsonIgnore //ไม่ส่งค่า password กลับไป
    @Column(nullable = false, length = 120)
    private String password;
    @Column(nullable = false, length = 60)
    private String name;
    private String civilId;
    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Social social;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Address> addresses;
}
