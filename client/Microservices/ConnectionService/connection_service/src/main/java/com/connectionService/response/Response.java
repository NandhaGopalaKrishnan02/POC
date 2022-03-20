package com.connectionService.response;

import java.util.HashMap;
import java.util.Map;

public class Response {
    static Map<Object, Object> responseDetails;
    static {
        responseDetails = new HashMap<>();
    }
    public static Map<Object, Object> message(String message) {
        responseDetails.put("message", message);
        return responseDetails;
    }
}
