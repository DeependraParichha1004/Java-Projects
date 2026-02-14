package com.example.Quiz.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public String createTinyUrl(@RequestBody CreateTinyUrl req) { // instead of creating a model can we some other way?
        // long id = System.currentTimeMillis();
        String long_url = req.getLong_url();
        return urlShortenService.createTinyUrl(long_url);

    }

    @GetMapping("/redirect/{tiny_url}")
    public ResponseEntity<String> redirect(@PathVariable String tiny_url) {
        // String tiny_url = entity.get("tiny_url");
        return urlShortenService.getLongUrl(tiny_url);
    }

}
