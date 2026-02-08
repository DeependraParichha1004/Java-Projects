package com.example.Quiz.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Quiz.Dao.SessionDetailDao;
import com.example.Quiz.models.SessionDetail;

import io.jsonwebtoken.Jwts;

@Service
public class UserService {

    @Autowired
    SessionDetailDao sessionDetailDao;
    // this service is used for user authentication

    public boolean authenticated(String username, String password) {
        Optional<SessionDetail> sessionDetailUser = sessionDetailDao.findByPassword(username);

        if (sessionDetailUser.isPresent()) {
            SessionDetail user = sessionDetailUser.get();
            if (user.getPassword().equals(password)) {
                System.out.println("Authentication Successful!!");
                return true;
            }
        }
        return false;
    }

    public SessionDetail loadByUsername(String username, String password) {
        Optional<SessionDetail> sessionDetailUser = sessionDetailDao.findByPassword(username);

        if (sessionDetailUser.get().getPassword() != null)
            return SessionDetail.builder()
                    .username(username)
                    .password(password)
                    .build();
        else {
            throw new RuntimeException("User not found: " + username); // how to build custom username not found
        }
    }

}