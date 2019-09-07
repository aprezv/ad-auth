package com.cloudcatalogs.adauth.utils;

import com.cloudcatalogs.userauthentication.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

/**
 * Created on 9/24/18.
 */
public class AuthUtils {

    public static String getDetailFromToken(String key){
        Map<String, String> details = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getDetails();
        return details.get(key);
    }

    public static User getCurrentUser(){
        try {
            return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }catch (Exception e){
            return null;
        }
    }
}