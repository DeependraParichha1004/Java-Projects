package com.example.Quiz.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Quiz.Dao.UrlShortenDao;
import com.example.Quiz.models.UrlShorten;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UrlShortenService {

    @Autowired
    UrlShortenDao urlShortenDao;

    public ResponseEntity<String> getLongUrl(String tiny_url) {
        String longUrl = urlShortenDao.findByTinyUrl(tiny_url);

        if (longUrl == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", longUrl);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    public String encode(long num) {
        // long num = System.currentTimeMillis(); // MUST be long
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int rem = (int) (num % 62);
            sb.append(characters.charAt(rem));
            num = num / 62;
        }
        return sb.reverse().toString();
    }

    public String create_url(String s) {
        return "https://dpmax.in/" + s;
    }

    public String createTinyUrl(String long_url) {
        String existingCode = urlShortenDao.findTinyUrlByLongUrl(long_url);
        if (existingCode != null) {
            return create_url(existingCode);
        } else {
            UrlShorten urlShorten = new UrlShorten();
            urlShorten.setLongUrl(long_url);
            urlShorten = urlShortenDao.save(urlShorten);
            String tiny_url = encode(urlShorten.getId());
            urlShorten.setTinyUrl(tiny_url);
            urlShorten = urlShortenDao.save(urlShorten);
            return create_url(tiny_url);
        }

    }

}
