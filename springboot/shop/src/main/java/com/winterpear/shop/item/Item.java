package com.winterpear.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
// JPA로 index 만들기
@Table(indexes = @Index(columnList = "title", name = "작명"))
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(columnDefinition = "TEXT")
    private String title;
    private Integer price;
}
