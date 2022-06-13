package com.example.simplechat;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Message")
public class Message extends ParseObject {
    public static final String USER_ID_KEY = "userId";
    public static final String BODY_KEY = "body";

    public void setBody(String body) {
        put(BODY_KEY, body);
    }

    public String getBody() {
        return getString(BODY_KEY);
    }

    public void setUserId(String user_id) {
        put(USER_ID_KEY, user_id);
    }

    public String getUserId() {
        return getString(USER_ID_KEY);
    }

}
