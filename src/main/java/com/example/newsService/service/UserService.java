package com.example.newsService.service;

import com.example.newsService.model.User;
import com.example.newsService.web.model.RequestFilter;

import java.util.List;

public interface UserService {

    List<User> findAll(RequestFilter filter);

    User findById(Long id);

    User save(User user);

    User  update(User user);

    void deleteById(Long id);
}
