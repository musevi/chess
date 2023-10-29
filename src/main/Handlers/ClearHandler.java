package Handlers;

import Results.ClearAppResult;
import Services.ClearAppService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ClearHandler extends BaseHandler {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        ClearAppService clearAppService = new ClearAppService();
        ClearAppResult result = clearAppService.ClearApp();
        return new Gson().toJson(result);
    }
}
