package com.example.newsService.mapper;

import com.example.newsService.model.User;
import com.example.newsService.web.model.user.UpsertUserRequest;
import com.example.newsService.web.model.user.UserListResponse;
import com.example.newsService.web.model.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {NewsMapper.class, CommentMapper.class})
public interface UserMapper {

    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);

    default UserListResponse userListToResponseList(List<User> users) {
        UserListResponse response = new UserListResponse();

        response.setUsers(users.stream()
                .map(this::userToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
