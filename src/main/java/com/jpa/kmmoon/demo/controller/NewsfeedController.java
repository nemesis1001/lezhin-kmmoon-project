package com.jpa.kmmoon.demo.controller;

import com.jpa.kmmoon.demo.service.BoardService;
import com.jpa.kmmoon.demo.service.FollowService;
import com.jpa.kmmoon.demo.vo.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RequestMapping("/newfeed")
@RestController
public class NewsfeedController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private FollowService followService;


    /**
     * 뉴스피드 - /newsfeed/{userUuid}/{page}/{size}            - get
     *
     * header Authorization 생략
     *
     * response
     * {
     *     status:200,
     *     msg:success,
     *     data:[]
     * }
     *
     */
    @RequestMapping(value = "/{userUuid}/{page}/{size}", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> userLog(
            @PathVariable("userUuid") String userUuid,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) throws Exception {

        log.info("userUuid : {}, page : {}, size : {}", userUuid, page, size);

        List<Board> logList = boardService.findByNewsfeed(userUuid, PageRequest.of(page, size));

        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("msg", "success");
        result.put("data", logList );

        return result;
    }
}
