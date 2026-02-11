package com.example.Quiz.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Quiz.models.UrlShorten;

@Repository
public interface UrlShortenDao extends JpaRepository<UrlShorten, Integer> {

    @Query(value = "select long_url from url_shorten where short_url=:tiny_url", nativeQuery = true)
    String findByTinyUrl(String tiny_url);
}
