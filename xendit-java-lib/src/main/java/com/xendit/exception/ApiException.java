package com.xendit.exception;

import java.util.Map;

public class ApiException extends XenditException {
    public ApiException(String message, String code, Map<String, Object> requestBody) {
        super(message, code, requestBody);
    }
}
