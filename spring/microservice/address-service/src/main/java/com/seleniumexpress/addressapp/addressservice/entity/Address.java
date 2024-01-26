package com.seleniumexpress.addressapp.addressservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="lane1")
    private String lane1;

    @Column(name="lane2")
    private String lane2;

    @Column(name="zip")
    private long zip;

    @Column(name="state")
    private String state;


}
