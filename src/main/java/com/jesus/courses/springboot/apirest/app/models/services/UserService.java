package com.jesus.courses.springboot.apirest.app.models.services;

import com.jesus.courses.springboot.apirest.app.models.dao.IUserDao;
import com.jesus.courses.springboot.apirest.app.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
            }
        };
    }

    public User save(User newuser) {
        if (newuser.getId() == null) {
            newuser.setCreatedAt(LocalDateTime.now());
        }
        newuser.setUpdatedAt(LocalDateTime.now());
        return userDao.save(newuser);
    }

    public Long count() {
        return userDao.count();
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }
}
