package com.driver.transformers;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;

import java.util.UUID;

public class UserTransformer {
    public static UserDto UserDetailsRequestModelToUserDto(UserDetailsRequestModel userDetailsRequestModel) {
        UserDto userDto = UserDto.builder()
                .userId(String.valueOf(UUID.randomUUID()))
                .email(userDetailsRequestModel.getEmail())
                .firstName(userDetailsRequestModel.getFirstName())
                .lastName(userDetailsRequestModel.getLastName())
                .build();
        return userDto;
    }

    public static UserResponse userDtoToUserResponse(UserDto userDto) {
        UserResponse userResponse = UserResponse.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .userId(userDto.getUserId())
                .lastName(userDto.getLastName())
                .build();
        return userResponse;
    }

    public static UserEntity userDtoToUser(UserDto userDto) {
        UserEntity user = UserEntity.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userId(userDto.getUserId())
                .build();
        return user;
    }

    public static UserDto userToUserDto(UserEntity userEntity) {
        UserDto userDto = UserDto.builder()
                .userId(userEntity.getUserId())
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build();
        return userDto;
    }
}
