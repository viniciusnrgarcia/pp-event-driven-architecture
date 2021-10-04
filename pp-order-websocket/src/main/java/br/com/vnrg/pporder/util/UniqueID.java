package br.com.vnrg.pporder.util;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class UniqueID {

    private UniqueID() {
    }

    public static String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static byte[] getUniqueIdBytes() {
         return getUniqueId().getBytes(StandardCharsets.UTF_8);
    }
}
