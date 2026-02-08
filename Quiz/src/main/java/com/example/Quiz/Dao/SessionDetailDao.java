package com.example.Quiz.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Quiz.models.SessionDetail;

@Repository
public interface SessionDetailDao extends JpaRepository<SessionDetail, Integer> {

    @Query(value = "select * from session_detail where username=:username", nativeQuery = true)
    Optional<SessionDetail> findByPassword(String username);
}
