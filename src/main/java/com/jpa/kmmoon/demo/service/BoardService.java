package com.jpa.kmmoon.demo.service;

import com.jpa.kmmoon.demo.repository.BoardRepository;
import com.jpa.kmmoon.demo.repository.FollowRepository;
import com.jpa.kmmoon.demo.vo.Board;
import com.jpa.kmmoon.demo.vo.Follow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private FollowRepository followRepository;

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public List<Board> findByNewsfeed(String userUuid, Pageable pageable){
        List<Follow> followList = followRepository.findByFollowee(userUuid);

        Collection<String> followerCollection = new HashSet<>();
        // 팔로워 리스트에 나 자신을 포함
        followerCollection.add(userUuid);
        for (Follow item : followList){
            followerCollection.add(item.getFollower());
        }
        log.info("followerCollection : {}", followerCollection);

        return boardRepository.findByUser_UserUuidIn(followerCollection, pageable);
    }




    public void save(Board board){
        boardRepository.save(board);
    }



}
