import Handlers.*;
import com.google.gson.Gson;
import spark.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        new Server().run();
    }

    private void run() {
        Spark.port(8080);
        Spark.externalStaticFileLocation("web");

        Spark.delete("/db", new ClearHandler());
        Spark.post("/session",  new LoginHandler());
        Spark.post("/user", new RegisterHandler());
        Spark.delete("/session", new LogoutHandler());
        Spark.post("/game", new CreateGameHandler());
        Spark.put("/game", new JoinGameHandler());
        Spark.get("/game", new ListGamesHandler());

    }
}
