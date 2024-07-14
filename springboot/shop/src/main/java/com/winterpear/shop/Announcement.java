package com.winterpear.shop;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Announcement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    
    public String subject;
    
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate date;
}
