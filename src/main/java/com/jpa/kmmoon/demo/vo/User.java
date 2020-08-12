package com.jpa.kmmoon.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GenericGenerator(name="user_uuid", strategy = "uuid")
    @Column(name = "user_uuid", nullable = false, length=36)
    private String userUuid;

    @Column(name = "email", nullable = false, length=100)
    private String email;

    @Column(name = "name", nullable = false, length=30)
    private String name;

    @Column(name = "enable", nullable = false)
    @ColumnDefault("1")
    private int enable;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false, length=20)
    private RoleType roleType;

    @Column(name = "reg_date", nullable = false)
    private Date regDate;


}
