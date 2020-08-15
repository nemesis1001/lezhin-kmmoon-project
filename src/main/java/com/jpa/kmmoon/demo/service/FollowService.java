package com.jpa.kmmoon.demo.service;

import com.jpa.kmmoon.demo.repository.FollowRepository;
import com.jpa.kmmoon.demo.vo.Follow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    public List<Follow> findByFollowee(String userUuid){
        return followRepository.findByFollowee(userUuid);
    }

    public void save(Follow follow){
        followRepository.save(follow);
    }


}
