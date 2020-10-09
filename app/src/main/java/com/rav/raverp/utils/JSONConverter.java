package com.rav.raverp.utils;

import com.google.gson.Gson;

public class JSONConverter {
    private Gson gson;
    private static JSONConverter parser;
    private static final String TAG = JSONConverter.class.getSimpleName();

    private JSONConverter() {
        super();
        gson = new Gson();
    }

    public static JSONConverter getInstance() {
        if (null == parser) {
            parser = new JSONConverter();
        }
        return parser;
    }

    public Object getModel(Class<?> modelClass, String jsonString) {
        Object modelObject = null;
        try {
            modelObject = gson.fromJson(jsonString, modelClass);
        } catch (NullPointerException e) {
            Logger.e(TAG, e.getMessage());
        }
        return modelObject;
    }

    public String getString(Object modelObject) {
        return gson.toJson(modelObject);
    }
}
