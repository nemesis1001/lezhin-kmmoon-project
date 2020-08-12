package com.jpa.kmmoon.demo.vo;

import com.jpa.kmmoon.demo.service.BoardService;
import com.jpa.kmmoon.demo.service.FollowService;
import com.jpa.kmmoon.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Slf4j
@SpringBootTest
class UnitTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private FollowService followService;


//    @Test
    public void userRegistInfo() {
        String userUuid = "";
        User user = null;

        userUuid = "22e6f869-43b1-4be8-89ae-69ea21e8e769";
        user = User.builder().userUuid(userUuid).email("nemesis1825@gmail.com").name("MKM").regDate(new Date()).roleType(RoleType.ADMIN).build();
        userService.save(user);


        for (int i = 0; i<=5; i++){
            userUuid = UUID.randomUUID().toString();
            user = User.builder().userUuid(userUuid).email("nemesis1825@gmail.com" + i).name("MKM_" + i).regDate(new Date()).roleType(RoleType.USER).build();
            userService.save(user);
        }

        User resultUser = userService.findByUserUuid(userUuid);
        log.info("result : {}, {}", resultUser, user);
    }

//    @Test
    public void followRegistInfo() {
        String followerUuid = "22e6f869-43b1-4be8-89ae-69ea21e8e769";
        String followeeUuid = "5ebda7ab-e4e7-46be-821c-65a64044d682";

        Follow follow = new Follow();
        follow.setFollowee(followeeUuid);
        follow.setFollower(followerUuid);

        log.info("follow : {} ", follow);

        followService.save(follow);

        List<Follow> resultFollow = followService.findByFollowee(followeeUuid);
        log.info("result : {}", resultFollow);
    }

//    @Test
    public void boardRegistInfo() {
        String userUuid = "22e6f869-43b1-4be8-89ae-69ea21e8e769";

        User user = userService.findByUserUuid(userUuid);

        Board board = new Board();
        board.setUser(user);
        board.setContent("fallowerContent1");
        board.setEnable(1);

        boardService.save(board);

        board = new Board();

        board.setUser(user);
        board.setContent("fallowerContent2");
        board.setEnable(1);

        boardService.save(board);

        board = new Board();

        board.setUser(user);
        board.setContent("fallowerContent3");
        board.setEnable(1);

        boardService.save(board);

    }

    @Test
    public void getNewsfeedList() {
        String userUuid = "c928c57d-63a9-4576-bcf7-57e23cd58cd7";

        List<Board> newsfeedList = boardService.findByNewsfeed(userUuid, PageRequest.of(0, 10));

        log.info("newsfeedList : {}", newsfeedList);

    }

}