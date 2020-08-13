package com.jpa.kmmoon.demo.repository;

import com.jpa.kmmoon.demo.vo.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {
    List<Follow> findByFollowee(String userUuid);
}
