package org.study.user.application;

import org.springframework.stereotype.Service;
import org.study.user.application.dto.CreateUserRequestDto;
import org.study.user.application.dto.GetUserResponseDto;
import org.study.user.application.interfaces.UserRepository;
import org.study.user.domain.User;
import org.study.user.domain.UserInfo;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);

        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }

}
