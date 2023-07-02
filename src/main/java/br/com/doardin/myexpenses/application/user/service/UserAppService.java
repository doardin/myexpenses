package br.com.doardin.myexpenses.application.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.doardin.myexpenses.application.user.dto.PostUserSignInDto;
import br.com.doardin.myexpenses.application.user.dto.PostUserSignUpDto;
import br.com.doardin.myexpenses.application.user.dto.ResponseUserSignInDto;
import br.com.doardin.myexpenses.application.user.dto.ResponseUserSignUpDto;
import br.com.doardin.myexpenses.domain.user.UserRepository;
import br.com.doardin.myexpenses.exceptions.ApiCustomException;
import br.com.doardin.myexpenses.mapper.UserMapper;
import br.com.doardin.myexpenses.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAppService {

    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    private final UserMapper mapper;
    private final UserRepository repository;

    public ResponseUserSignUpDto signUp(PostUserSignUpDto dto) {
        var user = mapper.toUser(dto);
        user = repository.save(user);
        return this.mapper.toResponseUserDto(user);
    }

    public ResponseUserSignInDto signIn(PostUserSignInDto dto) {
        var user = this.repository.findByEmail(dto.email()).orElse(null);

        if (Boolean.FALSE.equals(encoder.matches(dto.password(), user.getPassword()))) {
            throw ApiCustomException.builder()
                    .message("Incorrect password")
                    .responseStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }

        var token = this.jwtUtil.createJwtForClaims(user.getId(), null);
        return ResponseUserSignInDto.builder()
                .token(token)
                .build();
    }

}
