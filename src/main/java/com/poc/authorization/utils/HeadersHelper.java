package com.poc.authorization.utils;

public class HeadersHelper {

    public enum TokenType {
        BASE_64,
        BEARER
    }

    public static String extractToken(String authorizationHeader, TokenType tokenType) {
        return switch (tokenType) {
            case BASE_64 -> authorizationHeader.replaceFirst("(?i)basic ", "");
            case BEARER -> authorizationHeader.replaceFirst("(?i)bearer ", "");
        };
    }
}
