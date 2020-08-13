package com.jpa.kmmoon.demo.vo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private int commentId;

    @Column(name = "content", nullable = false, length=1000)
    private String content;

    @Column(name = "reg_date", nullable = false)
    private Date regDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", nullable = false, foreignKey = @ForeignKey(name = "fk_user_uuid_to_board_id"))
    private User user;

    @ManyToOne(targetEntity = Board.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false, foreignKey = @ForeignKey(name = "fk_board_id_to_comment_id"))
    private Board board;
}
