package com.jpa.kmmoon.demo.controller;

//import com.jpa.kmmoon.demo.repository.NoteRepository;
//import com.jpa.kmmoon.demo.vo.Note;
import com.jpa.kmmoon.demo.service.BoardService;
import com.jpa.kmmoon.demo.vo.Board;
import com.jpa.kmmoon.demo.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/posting")
@RestController
public class PostingController {

    @Autowired
    private BoardService boardService;


    /**
     * 포스팅 - /posting/info            - post
     * header Authorization 생략
     *
     * body
     * {
     *     "userUuid":{userUuid},
     *     "content":{content}
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
    public @ResponseBody
    HashMap<String, Object> boardRegistInfo(@RequestBody HashMap<String, String> requstMap) {
        log.info("note : {}", requstMap);

        User user = new User();
        user.setUserUuid(requstMap.get("userUuid"));

        Board board = new Board();
        board.setContent(requstMap.get("content"));
        board.setUser(user);

        boardService.save(board);

        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("msg", "success");

        return result;
    }
}
