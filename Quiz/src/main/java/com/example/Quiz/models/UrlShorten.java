package com.example.Quiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "url_shorten")
@Data
public class UrlShorten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String longUrl;
    private String shortUrl;

    public void setLongUrl(String long_url) {
        this.longUrl = long_url;
    }

    public void setTinyUrl(String tiny_url) {
        this.shortUrl = tiny_url;
    }
}
