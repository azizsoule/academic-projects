package com.app.ecommerce.utils;

public class Router {
    public static String redirectTo(String uri){
        return String.format("redirect:%s",uri);
    }
}
