package com.xendit;

public class Xendit {
    public static final String LIVE_URL = "https://api.xendit.co";

    public static volatile String apiKey;

    public static volatile String apiUrl = LIVE_URL;

    public static String getUrl() {
        return apiUrl;
    }

    public static String getApiKey() {
        return apiKey;
    }
}
