package com.jpa.kmmoon.demo.vo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class FollowPK implements Serializable {
    private String follower;
    private String followee;
}
