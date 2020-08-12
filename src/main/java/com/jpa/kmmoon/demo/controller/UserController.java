package com.jpa.kmmoon.demo.controller;

import com.jpa.kmmoon.demo.repository.UserRepository;
import com.jpa.kmmoon.demo.service.UserService;
import com.jpa.kmmoon.demo.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userUuid}")
    public @ResponseBody HashMap<String, Object> getUserUuid(@PathVariable String userUuid) {
        log.info("user");
        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("msg", "success");
        result.put("data", userService.findByUserUuid(userUuid) );
        // newsFeed엔 follower 리스트만 필요
        return result;
    }

    @PostMapping("/info")
    public @ResponseBody HashMap<String, Object> userRegistInfo(@RequestBody User user) {
        log.info("note : {}", user);
        userService.save(user);

        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("msg", "success");

        return result;
    }
}
