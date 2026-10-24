package com.app.ecommerce.utils;

import java.util.UUID;

public class Generator {

    public static String generate(int taille) {
        UUID uuid = UUID.randomUUID();
        String string = uuid.toString();
        int uid = string.hashCode();
        String filterStr = ""+uid;
        string = filterStr.replaceAll("-", "");
        return string.substring(0, taille);
    }

}
