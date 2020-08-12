package com.jpa.kmmoon.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FollowPK.class)
@Table(name = "Follow")
public class Follow implements Serializable {

    @Id
    @Column(name = "follower", nullable = false, length = 36)
    private String follower;

    @Id
    @Column(name = "followee", nullable = false, length = 36)
    private String followee;

    @Column(name = "reg_date", nullable = false)
    @CreationTimestamp
    private Date regDate;

}
