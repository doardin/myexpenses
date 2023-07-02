package br.com.doardin.myexpenses.application.user.service;

import org.springframework.stereotype.Service;

import br.com.doardin.myexpenses.application.user.dto.PostUserSignUpDto;
import br.com.doardin.myexpenses.application.user.dto.ResponseUserSignUpDto;
import br.com.doardin.myexpenses.domain.user.UserRepository;
import br.com.doardin.myexpenses.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAppService {

    private final UserMapper mapper;
    private final UserRepository repository;

    public ResponseUserSignUpDto signUp(PostUserSignUpDto dto) {
        var user = mapper.toUser(dto);
        user = repository.save(user);
        return this.mapper.toResponseUserDto(user);
    }
}
