package com.jpa.kmmoon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJpaKmmoonApplication {

    /**
    * ERD 링크 : https://www.erdcloud.com/d/9asxDWaNpEqhNWky3
    * 구현해야하는 기술들
    * 1. 팔로우 - /follow/info             - post
    * header Authorization 생략
    *
    * body
    * {
    *     "follower":{userUuid}, -- 팔로우 된 사람
    *     "followee":{userUuid}  -- 팔로우 하는 사람
    * }
    * 2. 포스팅 - /posting/info            - post
    * header Authorization 생략
    *
    * body
    * {
    *     "userUuid":{userUuid},
    *     "content":{content}
    * }
    *
    * 3. 뉴스 피드 /newsfeed/{userUuid}    - get
    * header Authorization 생략
    *
    * ExceptionHandler 간략하게 추가
    *
    * Test 코드를 작성하여 데이터 USER 테이블 데이터 INSERT 후 테스트 진행.
    *
    * */

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaKmmoonApplication.class, args);
    }

}
