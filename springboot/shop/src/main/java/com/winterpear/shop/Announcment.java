package com.winterpear.shop;

import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Announcment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    
    public String subject;
    
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate date;
}
