package com.jpa.kmmoon.demo.service;

import com.jpa.kmmoon.demo.repository.UserRepository;
import com.jpa.kmmoon.demo.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByUserUuid(String userUuid){
        return userRepository.findByUserUuid(userUuid);
    }

    public void save(User user){
        userRepository.save(user);
    }


}
