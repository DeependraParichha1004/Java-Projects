package com.example.Quiz.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "session_detail")
@Data
@Builder
@NoArgsConstructor // Essential for Jackson/JPA
@AllArgsConstructor
public class SessionDetail {
    @Id
    private Integer id;
    private String username;
    private String password;

}
