package br.com.doardin.myexpenses.util;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public String createJwtForClaims(String subject,
            Collection<? extends GrantedAuthority> grantedAuthorities) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Instant.now().toEpochMilli());
        calendar.add(Calendar.HOUR, (24));

        JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);
        if (grantedAuthorities != null) {
            List<String> authorities = new ArrayList<>();
            for (GrantedAuthority authority : grantedAuthorities) {
                authorities.add(authority.getAuthority());
            }
        }
        return jwtBuilder.withNotBefore(new Date())
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.RSA256(this.publicKey, this.privateKey));

    }

}
