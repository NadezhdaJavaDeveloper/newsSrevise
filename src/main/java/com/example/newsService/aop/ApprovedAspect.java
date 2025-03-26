package com.example.newsService.aop;

import com.example.newsService.exception.AccessDeniedException;
import com.example.newsService.exception.EntityNotFoundException;
import com.example.newsService.model.Comment;
import com.example.newsService.model.Editable;
import com.example.newsService.model.News;
import com.example.newsService.repository.CommentRepository;
import com.example.newsService.repository.NewsRepository;
import com.example.newsService.service.CommentService;
import com.example.newsService.service.NewsService;
import com.example.newsService.service.UserService;
import com.example.newsService.service.annotationForParametrs.EntityId;
import com.example.newsService.service.annotationForParametrs.UserId;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ApprovedAspect {

    @Autowired
    private UserService userService;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CommentRepository commentRepository;

    private static final String NEWS_TYPE = "news";
    private static final String COMMENT_TYPE = "comment";

    @Before("@annotation(Approved)")
    public void verifyingUserRightsToEditOrDelete(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        Editable entity = null;
        Long userId = null;
        Long entityId = null;
        Class<?> entityType = null;

        // public News updateNews(News news, @UserId Long userId) {
        // public void deleteNewsById(@EntityId Long id, @UserId Long userId) {

        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof UserId) {
                    userId = (Long) args[i];
                } else if (annotation instanceof EntityId) {
                    entityId = (Long) args[i];
                    entityType = determineEntityType(method);
                }
            }
        }

        if (userId == null) {
            throw new IllegalArgumentException("Необходимо указать и id пользователя");
        }

        if(entityId == null) {
            entityId = extractEntityIdFromArgs(args);
            entityType = determineEntityType(method);
        }

        if(!entityExists(entityType, entityId)) {
            throw new EntityNotFoundException("Сущность не найдена");
        }

        Set<Long> userEntitiesIds = getUserEntitiesIds(userId, entityType);

        if(!userEntitiesIds.contains(entityId)) {
            throw new AccessDeniedException("Редактирование/удаление новости/комментария доступно только автору новости");
        }

//        if (entityId != null && entityType != null) {
//            entity = fetchEntity(entityId, entityType);
//        } else {
//            for (Object arg : args) {
//                if (arg instanceof Editable) {
//                    entity = (Editable) arg;
//                }
//            }
//        }
//
//
//        if (entity == null) {
//            throw new IllegalArgumentException("Необходимо указать сущность: новость или комментарий");
//        }
//
//        if(entity instanceof News) {
//
//            List<News> newsList = userService.findById(userId).getNewsList();
//
//            List<Long> newsIdList = newsList.stream().map(News::getId).collect(Collectors.toList());
//
//            if (!newsIdList.contains(entity.getId())) {
//                throw new AccessDeniedException("Редактирование новости доступно только автору новости");
//            }
//        } else if (entity instanceof Comment) {
//
//            List<Comment> commentList = userService.findById(userId).getCommentList();
//
//            List<Long> commentsIdList = commentList.stream().map(Comment::getId).collect(Collectors.toList());
//
//            if (!commentsIdList.contains(entity.getId())) {
//                throw new AccessDeniedException("Редактирование комеентария доступно только автору новости");
//            }
//
//        }
//
//        log.info("Requested action is acceptable to the user {}", userId);

    }

    private Set<Long> getUserEntitiesIds(Long userId, Class<?> entityType) {
        if(entityType == News.class) {
            return newsRepository.findNewsIdByUserId(userId);
        } else if(entityType == Comment.class) {
            return commentRepository.findCommentsIdByUserId(userId);
        }
        throw  new IllegalArgumentException("Неподдерживаемый тип сущности");
    }

    private boolean entityExists(Class<?> entityType, Long entityId) {
        if(entityType == News.class) {
            return newsRepository.existsById(entityId);
        } else if(entityType == Comment.class) {
            return commentRepository.existsById(entityId);
        }
        return false;
    }

    private Long extractEntityIdFromArgs(Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg instanceof Editable)
                .map(arg -> ((Editable) arg).getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Id сущности не найден"));
    }

//    private Editable fetchEntity(Long entityId, Class<?> entityType) {
//        if (entityType.equals(News.class)) {
//            return newsService.findById(entityId);
//        }
//        if (entityType.equals(Comment.class)) {
//            return commentService.findById(entityId);
//        }
//        throw new IllegalArgumentException("Неподдерживаемый тип сущности");
//    }

    private Class<?> determineEntityType(Method method) {

        String methodName = method.getName().toLowerCase();
        if (methodName.contains(NEWS_TYPE)) {
            return News.class;
        }
        if (methodName.contains(COMMENT_TYPE)) {
            return Comment.class;
        }
        throw new IllegalArgumentException("Неподдерживаемый тип сущности");
    }
}
