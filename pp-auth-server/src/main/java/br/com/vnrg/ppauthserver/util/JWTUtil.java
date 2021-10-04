package br.com.vnrg.ppauthserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public final class JWTUtil {

    // fixme
    private static final String SECRET_KEY = "#@*&R@21219323_+023029";
    private static final long EXPIRATION_TIME = 1_000_000_000;
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    private JWTUtil() {
    }

    public static String getToken(final Map<String, ?> payload) {
        return JWT.create()
                .withPayload(payload)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    public static String getClaim(final String token, final String payload) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(payload).asString();
    }
}
