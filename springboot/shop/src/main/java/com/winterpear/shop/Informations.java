package com.winterpear.shop;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Informations {
    private String name;
    private Integer age;

    public void addAge() {
        this.age = this.age + 1;
    }

    public void setAge(Integer age) {
        if (age <= 100 && age >= 0) {
            this.age = age;
        }
    }
}

class Test {
    public static void main(String[] args) {
        Informations info = new Informations();

        info.addAge();
        info.setAge(12);
    }
}
