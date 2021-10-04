package br.com.vnrg.pporder.util;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

public final class HashGenerator {

    private HashGenerator() {
    }

    public static String get() {
        return UUID.randomUUID().toString();
    }

    public static byte[] getBytes() {
        return getBytes(get());
    }

    public static byte[] getBytes(final String uuid) {
        return uuid.getBytes(StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static String generateHex(final String... values) {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        var builder = new StringBuilder();
        for (Object p : values) {
            builder.append(p);
        }
        return Hex.encodeHexString(md.digest(builder.toString().getBytes(StandardCharsets.UTF_8)));
    }
}