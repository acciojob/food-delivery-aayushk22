package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import com.driver.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception {

        UserEntity user1 = userRepository.findByEmail(user.getEmail());
        if (user1 != null) {
            throw new Exception();
        }

        UserEntity userEntity = UserTransformer.userDtoToUser(user);

        UserEntity savedUserEntity = userRepository.save(userEntity);

        UserDto response = UserTransformer.userToUserDto(savedUserEntity);
        return response;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        return null;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        return null;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        return null;
    }

    @Override
    public void deleteUser(String userId) throws Exception {

    }

    @Override
    public List<UserDto> getUsers() {
        return null;
    }
}