package com.jpa.kmmoon.demo.controller;

import com.jpa.kmmoon.demo.repository.FollowRepository;
import com.jpa.kmmoon.demo.service.FollowService;
import com.jpa.kmmoon.demo.vo.Follow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/follow")
@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/uuid/{userUuid}")
    public HashMap<String, Object> getUserUuid(@PathVariable String userUuid) {
        log.info("userUuid : {}", userUuid);

        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("msg", "success");
        result.put("data", followService.findByFollowee(userUuid) );
        return result;
    }

    /**
     * 팔로우 - /follow/info            - post
     * header Authorization 생략
     *
     * body
     * {
     *     "followee":{userUuid},
     *     "follower":{userUuid}
     * }
     *
     * response
     * {
     *     status:200,
     *     msg:success
     * }
     *
     */
    @PostMapping("/info")
    public HashMap<String, Object> userRegistInfo(@RequestBody Follow follow) {
        log.info("note : {}", follow);

        followService.save(follow);

        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("msg", "success");

        return result;
    }
}
