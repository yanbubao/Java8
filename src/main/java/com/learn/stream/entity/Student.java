package com.learn.stream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: yanzx
 * @date: 2019/08/13 22:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    private String name;

    private int score;
}
