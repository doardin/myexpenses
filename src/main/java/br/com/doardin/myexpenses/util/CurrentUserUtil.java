package br.com.doardin.myexpenses.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.doardin.myexpenses.domain.user.User;
import br.com.doardin.myexpenses.domain.user.UserRepository;
import br.com.doardin.myexpenses.exceptions.ApiCustomException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {

    private final UserRepository repository;

    public User getUserFromSecurityContextHolder() {
        var userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.repository.findById(userId)
                .orElseThrow(() -> ApiCustomException.builder()
                        .message("User not found")
                        .build());
    }

}
