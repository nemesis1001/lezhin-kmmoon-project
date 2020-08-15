package com.jpa.kmmoon.demo.vo;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Board")
public class Board implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id", nullable = false)
    private int boardId;

    @Column(name = "content", nullable = false, length=3000)
    private String content;

    @Column(name = "reg_date", nullable = false)
    @CreationTimestamp
    private Date regDate;

    @Column(name = "modify_date", nullable = true)
    private Date modifyDate;

    @Column(name = "enable", nullable = false)
    @ColumnDefault("1")
    private int enable;

    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = false, foreignKey = @ForeignKey(name = "fk_user_uuid_to_board_id"))
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="board_id")
    private List<Comment> commentList;

}
