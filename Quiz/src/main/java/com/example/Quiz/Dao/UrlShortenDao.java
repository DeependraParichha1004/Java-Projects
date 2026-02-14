package com.example.Quiz.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Quiz.models.UrlShorten;

@Repository
public interface UrlShortenDao extends JpaRepository<UrlShorten, Integer> {

    @Query(value = "select long_url from url_shorten where short_url=:tiny_url", nativeQuery = true) // check if it
                                                                                                     // works without
                                                                                                     // this query.

    String findByTinyUrl(String tiny_url);

    String findByLongUrl(String long_url);

    @Query(value = "SELECT short_url FROM url_shorten WHERE long_url=:long_url LIMIT 1", nativeQuery = true)
    String findTinyUrlByLongUrl(String long_url);
}
