package com.xendit;

import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;

public class Xendit {
    public static final String LIVE_URL = "https://api.xendit.co";

    public static volatile NetworkClient requestClient = new BaseRequest();

    public static volatile String apiKey;

    private static volatile String apiUrl = LIVE_URL;

    public static String getUrl() {
        return apiUrl;
    }

    public static String getApiKey() {
        return apiKey;
    }
}
