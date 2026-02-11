package com.example.Quiz.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.CreateTinyUrl;
import com.example.Quiz.Service.UrlShortenService;

@RestController
@RequestMapping("/url_shorten_service")
public class UrlShortenController {

    @Autowired
    UrlShortenService urlShortenService;

    // @Autowired
    // CreateTinyUrl createTinyUrl;

    @GetMapping("/get")
    public String getLongUrl(@RequestParam String tiny_url) {
        return urlShortenService.getLongUrl(tiny_url);
    }

    @PostMapping("/create")
    public String createTinyUrl(@RequestBody CreateTinyUrl req) {
        // long id = System.currentTimeMillis();
        String long_url = req.getLong_url();
        return urlShortenService.createTinyUrl(long_url);

    }

}
