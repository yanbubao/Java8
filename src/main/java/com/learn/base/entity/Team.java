package com.learn.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: yanzx
 * @date: 2019/08/07 01:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team implements Serializable {

    private String teamName;

    private List<Player> players;
}
