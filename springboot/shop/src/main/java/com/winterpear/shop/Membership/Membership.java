package com.winterpear.shop.Membership;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class Membership {
    @Id
    private String userid;
    
    @Column
    private String password;
    private String email;
    private String username;
}
