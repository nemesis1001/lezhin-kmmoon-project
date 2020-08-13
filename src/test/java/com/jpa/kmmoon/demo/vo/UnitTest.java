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

    private final String ADMIN_USER_UUID = "22e6f869-43b1-4be8-89ae-69ea21e8e769";
    private final String FOLLOWEE_USER_UUID = "ce4bbd14-e703-44cc-af83-3ce151362e06";
    private final String FOLLOWER_USER_UUID = "c51832f4-819d-49e6-b128-6b7b9546e247";
    private final String NOT_FOLLOWER_USER_UUID = "1f9d164d-4e96-47c4-bd8e-ff17f00902be";

    @Test
    public void test(){
        userRegistInfo(); // 유저 생성 테스트
        followRegistInfo(); // 팔로우 생성 테스트
        boardRegistInfo(); // 포스팅 테스트
        getNewsfeedList(); // 뉴스피드 테스트
    }

    // 테스트 유저 생성
    public void userRegistInfo() {
        User user = null;
        String userUuid = "";

        Date date = new Date();

        user = User.builder().userUuid(ADMIN_USER_UUID).email("nemesis1825@gmail.com").name("MKM").regDate(date).roleType(RoleType.ADMIN).build();
        userService.save(user);

        user = User.builder().userUuid(FOLLOWEE_USER_UUID).email("nemesis1825@gmail.com_FOLLOWEE").name("MKM_FOLLOWEE").regDate(date).roleType(RoleType.USER).build();
        userService.save(user);

        user = User.builder().userUuid(FOLLOWER_USER_UUID).email("nemesis1825@gmail.com_FOLLOWER").name("MKM_FOLLOWER").regDate(date).roleType(RoleType.USER).build();
        userService.save(user);

        user = User.builder().userUuid(NOT_FOLLOWER_USER_UUID).email("nemesis1825@gmail.com_NOT_FOLLOWER").name("MKM_NOT_FOLLOWER").regDate(date).roleType(RoleType.USER).build();
        userService.save(user);

        for (int i = 1; i<=5; i++){
            userUuid = UUID.randomUUID().toString();
            user = User.builder().userUuid(userUuid).email("nemesis1825@gmail.com" + i).name("MKM_" + i).regDate(date).roleType(RoleType.USER).build();
            userService.save(user);
        }

        List<User> resultUser = userService.findAll();
        log.info("userRegistInfo() result : {}", resultUser);
        log.info("==============================================================");
    }

    // 팔로우 테스트
    public void followRegistInfo() {

        Follow follow = new Follow();
        follow.setFollowee(FOLLOWEE_USER_UUID);
        follow.setFollower(FOLLOWER_USER_UUID);

        followService.save(follow);

        List<Follow> resultFollow = followService.findByFollowee(FOLLOWEE_USER_UUID);
        log.info("followRegistInfo() result : {}", resultFollow);
    }

    // 포스팅 테스트
    public void boardRegistInfo() {

        User user = userService.findByUserUuid(FOLLOWER_USER_UUID);

        Board board = null;

        // follower 컨텐츠 생성

        for (int i = 1; i<= 5; i++) {
            board = new Board();

            board.setUser(user);
            board.setContent("followerContent_" + i);
            board.setEnable(1);

            boardService.save(board);
        }

        // followee 컨텐츠 생성
        user = userService.findByUserUuid(FOLLOWEE_USER_UUID);

        for (int i = 1; i<= 5; i++) {
            board = new Board();

            board.setUser(user);
            board.setContent("followeeContent_" + i);
            board.setEnable(1);

            boardService.save(board);
        }

        user = userService.findByUserUuid(NOT_FOLLOWER_USER_UUID);

        board = new Board();

        board.setUser(user);
        board.setContent("notFollowerContent");
        board.setEnable(1);

        boardService.save(board);

        List<Board> boardList = boardService.findAll();

        log.info("boardRegistInfo() result : {}", boardList);
        log.info("==============================================================");
    }

    //뉴스피드 조회 테스트
    public void getNewsfeedList() {
        List<Board> newsfeedList = boardService.findByNewsfeed(FOLLOWEE_USER_UUID, PageRequest.of(0, 10));

        log.info("getNewsfeedList() result : {}", newsfeedList);
        log.info("==============================================================");
    }

}