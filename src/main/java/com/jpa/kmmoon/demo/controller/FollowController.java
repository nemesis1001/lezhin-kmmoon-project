package com.jpa.kmmoon.demo.controller;

//import com.jpa.kmmoon.demo.repository.NoteRepository;
//import com.jpa.kmmoon.demo.vo.Note;
import com.jpa.kmmoon.demo.repository.FollowRepository;
import com.jpa.kmmoon.demo.repository.UserRepository;
import com.jpa.kmmoon.demo.vo.Follow;
import com.jpa.kmmoon.demo.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/follow")
@RestController
public class FollowController {

    @Autowired
    private FollowRepository followRepository;

    @GetMapping("/uuid/{userUuid}")
    public @ResponseBody
    HashMap<String, Object> getUserUuid(@PathVariable String userUuid) {
        log.info("userUuid : {}", userUuid);

        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("msg", "success");
        result.put("data", followRepository.findByFollowee(userUuid) );
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
    public @ResponseBody HashMap<String, Object> userRegistInfo(@RequestBody Follow follow) {
        log.info("note : {}", follow);

        followRepository.save(follow);

        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("msg", "success");

        return result;
    }
}
