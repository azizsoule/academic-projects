package com.app.ecommerce.utils;
import java.util.Date;
import java.util.UUID;

public class Generator {
    public static String generate(int taille){
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return str.substring(0,taille);
    }
    public static void main(String [] args){
        System.out.println(new Date());
    }


}
