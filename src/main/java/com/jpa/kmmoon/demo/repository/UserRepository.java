package com.jpa.kmmoon.demo.repository;

import com.jpa.kmmoon.demo.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    User findByUserUuid(String userUuid);
}
