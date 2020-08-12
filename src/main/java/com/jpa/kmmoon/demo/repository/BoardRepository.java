package com.jpa.kmmoon.demo.repository;

import com.jpa.kmmoon.demo.vo.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
        List<Board> findByUser_UserUuidIn(Collection<String> followerCollection, Pageable pageable);
}
