package br.com.doardin.myexpenses.mapper;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.doardin.myexpenses.application.user.dto.PostUserSignUpDto;
import br.com.doardin.myexpenses.application.user.dto.ResponseUserSignUpDto;
import br.com.doardin.myexpenses.domain.user.User;

@Service
public class UserMapper {

    public User toUser(PostUserSignUpDto dto) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    public ResponseUserSignUpDto toResponseUserDto(User user) {
        return ResponseUserSignUpDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

}
