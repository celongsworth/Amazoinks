package com.example.amazoinks.database.typeConverters;

import androidx.room.TypeConverter;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonStringTypeConverter {

    @TypeConverter
    public String jsonToString(JSONObject data){
        return data.toString();
    }

    @TypeConverter
    public JSONObject stringToJson(String json) throws JSONException {
        return new JSONObject(json);
    }
}
