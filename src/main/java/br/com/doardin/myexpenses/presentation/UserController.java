package br.com.doardin.myexpenses.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.doardin.myexpenses.application.user.dto.PostUserSignInDto;
import br.com.doardin.myexpenses.application.user.dto.PostUserSignUpDto;
import br.com.doardin.myexpenses.application.user.dto.ResponseUserSignInDto;
import br.com.doardin.myexpenses.application.user.dto.ResponseUserSignUpDto;
import br.com.doardin.myexpenses.application.user.service.UserAppService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserAppService service;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUserSignUpDto signUp(@RequestBody @Valid PostUserSignUpDto dto) {
        return service.signUp(dto);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public ResponseUserSignInDto signIn(@RequestBody @Valid PostUserSignInDto dto) {
        return service.signIn(dto);
    }

}
