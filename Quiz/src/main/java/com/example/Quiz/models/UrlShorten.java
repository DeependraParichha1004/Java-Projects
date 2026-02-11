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
    private String long_url;
    private String short_url;

    public UrlShorten(String long_url, String tiny_url) {
        this.short_url = tiny_url;
        this.long_url = long_url;
    }

}
