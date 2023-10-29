package Handlers;

import com.google.gson.Gson;
import spark.Request;
import spark.Route;

public abstract class BaseHandler implements Route {

    protected static <T> T getBody(Request request, Class<T> clazz) {
        var body = new Gson().fromJson(request.body(), clazz);
        if (body == null) {
            throw new RuntimeException("missing required body");
        }
        return body;
    }
}
