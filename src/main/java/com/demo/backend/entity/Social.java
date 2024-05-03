package com.demo.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_social")
public class Social extends BaseEntity {
    @Column(length = 120)
    private String facebook;
    @Column(length = 120)
    private String line;
    @Column(length = 120)
    private String instagram;

    @OneToOne
    @JoinColumn(name = "m_user_id", nullable = false)
    private User user;
}
