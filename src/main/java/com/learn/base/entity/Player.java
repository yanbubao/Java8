package com.learn.base.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author: yanzx
 * @date: 2019/08/06 22:09
 */
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Player implements Serializable {

    private String name;

    private Integer age;

    public Player() {
        this.name = "default";
    }
}
