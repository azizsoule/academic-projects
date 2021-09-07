package com.app.ecommerce.utils;

public class Constants {
    public static final String PREFIX = "/api/v1";
    public static final String MEDIA_DIR="cashMedia/";
    public static final String USERS_MEDIA_DIR= MEDIA_DIR+"users";
    public static final String PARTNERS_MEDIA_DIR = MEDIA_DIR+"partners";
    public static boolean validate(String mdp){
        return mdp.equals("admin");
    }
    public static String goTo(String uri){
        return "redirect:"+uri;
    }
}
