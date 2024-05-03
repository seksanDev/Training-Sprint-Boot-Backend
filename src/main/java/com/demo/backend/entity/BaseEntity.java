package com.demo.backend.entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
@Data
@MappedSuperclass //เพื่อให้คลาสลูกนำไปใช้ได้
public abstract class BaseEntity {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;
}
