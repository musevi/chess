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
        ClearAppResult result = clearAppService.clearApp();

        if(result.getMessage() != null && result.getMessage().equals("Error: internal server error")) {response.status(500);}

        return new Gson().toJson(result);
    }
}
