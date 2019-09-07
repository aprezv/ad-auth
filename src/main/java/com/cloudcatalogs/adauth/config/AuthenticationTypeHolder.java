package com.cloudcatalogs.adauth.config;

/**
 * Created on 9/24/18.
 */
public class AuthenticationTypeHolder {

    public static ThreadLocal<AuthenticationType> authenticationType = new ThreadLocal<>();

    public static AuthenticationType getAuthenticatioType() {
        return authenticationType.get();
    }

    public static void setAuthenticationType(AuthenticationType type) {
        authenticationType.set(type);
    }

    public enum AuthenticationType{
        ADMIN
    }
}
