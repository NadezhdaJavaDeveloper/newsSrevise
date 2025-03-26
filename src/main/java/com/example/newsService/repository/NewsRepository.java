package com.example.newsService.repository;

import com.example.newsService.model.News;
import com.example.newsService.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {


    @Override
    @EntityGraph(attributePaths = {"category", "user", "commentList"})
    Page<News> findAll(Specification<News> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"category", "user", "commentList"})
    Page<News> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"category", "user", "commentList"})
    Optional<News> findById(Long aLong);

    @Override
    @EntityGraph(attributePaths = {"category", "user", "commentList"})
    <S extends News> S save(S entity);

    @Query(value = "SELECT id FROM news WHERE user_id =:userId", nativeQuery = true)
    Set<Long> findNewsIdByUserId(Long userId);
}
