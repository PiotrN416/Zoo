package com.dq.zoo.helpers;

import com.dq.zoo.model.Entity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
    public static Entity fromJson(String json, Class<? extends Entity> type) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(json);
        return gson.fromJson(object, type);
    }

    public static String toJson(Object object, Class<?> type) {
        Gson gson = new Gson();
        return gson.toJson(object, type);
    }
}
