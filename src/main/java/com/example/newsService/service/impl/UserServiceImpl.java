package com.example.newsService.service.impl;

import com.example.newsService.exception.EntityNotFoundException;
import com.example.newsService.model.User;
import com.example.newsService.repository.UserRepository;
import com.example.newsService.service.UserService;
import com.example.newsService.utils.BeanUtils;
import com.example.newsService.web.model.RequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll(RequestFilter filter) {
        return userRepository.findAll(PageRequest.of(
                filter.getPageNumber(), filter.getPageSize()
                )).getContent();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Пользователь с ID {0} не найден", id
                )));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(existedUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
