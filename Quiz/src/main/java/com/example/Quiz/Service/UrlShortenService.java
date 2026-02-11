package com.example.Quiz.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Quiz.Dao.UrlShortenDao;
import com.example.Quiz.models.UrlShorten;

@Service
public class UrlShortenService {

    @Autowired
    UrlShortenDao urlShortenDao;

    public String getLongUrl(String tiny_url) {
        return urlShortenDao.findByTinyUrl(tiny_url);
    }

    public String createTinyUrl(String long_url) {
        long num = System.currentTimeMillis(); // MUST be long
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int rem = (int) (num % 62);
            sb.append(characters.charAt(rem));
            num = num / 62;
        }

        String tiny_url = sb.reverse().toString();

        // Save the mapping to your database here!
        // Assuming your Entity has a constructor like: UrlEntity(longUrl, tinyUrl)
        // urlShortenDao.save(new UrlEntity(long_url, tinyUrl));
        UrlShorten urlShorten = new UrlShorten(long_url, tiny_url);
        urlShortenDao.save(urlShorten);
        return "https://tiny_url.com/" + tiny_url;
    }

}
