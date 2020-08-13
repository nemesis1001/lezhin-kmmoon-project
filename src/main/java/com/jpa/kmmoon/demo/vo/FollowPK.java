package com.jpa.kmmoon.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FollowPK implements Serializable {
    private String follower;
    private String followee;
}
