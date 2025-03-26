package com.example.newsService.repository;

import com.example.newsService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll (Pageable pageable);
}
