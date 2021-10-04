package br.com.vnrg.ppauthserver.service;

import br.com.vnrg.ppauthserver.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AuthenticationService {

    public String getToken(final String username) {
        return JWTUtil.getToken(Map.of("user", username));
    }

    public String validate(final String token) {
        return JWTUtil.getClaim(token, "user");
    }
}
