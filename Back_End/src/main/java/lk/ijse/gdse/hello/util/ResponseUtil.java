package lk.ijse.gdse.hello.util;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class ResponseUtil {

    public static JsonObject getJson(String state, String message, JsonArray...data){
        JsonObjectBuilder object = Json.createObjectBuilder();
        object.add("state",state);
        object.add("message",message);
        if (data.length>0){
            object.add("data",data[0]);
        }else{
            object.add("data","");
        }

        return object.build();
    }

}
