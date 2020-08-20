package com.jpa.kmmoon.demo.unit;

import com.jpa.kmmoon.demo.service.BoardService;
import com.jpa.kmmoon.demo.service.FollowService;
import com.jpa.kmmoon.demo.service.UserService;
import com.jpa.kmmoon.demo.vo.Board;
import com.jpa.kmmoon.demo.vo.Follow;
import com.jpa.kmmoon.demo.vo.RoleType;
import com.jpa.kmmoon.demo.vo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.UUID;


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
        userRegistInfo(ADMIN_USER_UUID, "nemesis1825@gmail.com","MKM", new Date(),RoleType.ADMIN);
        userRegistInfo(FOLLOWEE_USER_UUID, "nemesis1825@gmail.com_FOLLOWEE","MKM_FOLLOWEE", new Date(),RoleType.USER);
        userRegistInfo(FOLLOWER_USER_UUID, "nemesis1825@gmail.com_FOLLOWER","MKM_FOLLOWER", new Date(),RoleType.USER);
        userRegistInfo(NOT_FOLLOWER_USER_UUID, "nemesis1825@gmail.com_NOT_FOLLOWER","MKM_NOT_FOLLOWER", new Date(),RoleType.USER);

        for (int i = 1; i<=5; i++){
            userRegistInfo(UUID.randomUUID().toString(), "nemesis1825@gmail.com" + i,"MKM_" + i, new Date(),RoleType.USER);
        }

//        log.info("userRegistInfo() result : {}", userService.findAll());
//        log.info("==============================================================");
    }

    // 팔로우 테스트
    public void followRegistInfo() {
        Follow follow = new Follow();
        follow.setFollowee(FOLLOWEE_USER_UUID);
        follow.setFollower(FOLLOWER_USER_UUID);

        followService.save(follow);

        List<Follow> resultFollow = followService.findByFollowee(FOLLOWEE_USER_UUID);
//        log.info("followRegistInfo() result : {}", resultFollow);
    }

    // 포스팅 테스트
    public void boardRegistInfo() {

        User user = userService.findByUserUuid(FOLLOWER_USER_UUID);

        Board board = null;

        // follower 컨텐츠 생성

        for (int i = 1; i<= 5; i++) {
            boardService.save(toBoard(user, "followerContent_" + i));
        }

        // followee 컨텐츠 생성
        user = userService.findByUserUuid(FOLLOWEE_USER_UUID);

        for (int i = 1; i<= 5; i++) {
            boardService.save(toBoard(user, "followeeContent_" + i));
        }

        // 무관계인 사용자의 컨텐츠 생성
        user = userService.findByUserUuid(NOT_FOLLOWER_USER_UUID);

        boardService.save(toBoard(user, "notFollowerContent"));

        List<Board> boardList = boardService.findAll();

//        log.info("boardRegistInfo() result : {}", boardList);
//        log.info("==============================================================");
    }

    //뉴스피드 조회 테스트
    public void getNewsfeedList() {
//        log.info("getNewsfeedList() result : {}", boardService.findByNewsfeed(FOLLOWEE_USER_UUID, PageRequest.of(0, 10)));
//        log.info("==============================================================");
    }


    private void userRegistInfo(String uuid, String email, String name, Date date, RoleType roleType){
        userService.save(User.builder().userUuid(uuid).email(email).name(name).regDate(date).roleType(roleType).build());
    }

    private Board toBoard(User user, String content){
        Board board = new Board();
        board.setUser(user);
        board.setContent(content);
        board.setEnable(1);
        return board;
    }

}