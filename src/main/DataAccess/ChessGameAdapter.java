package DataAccess;

import chess.ChessGame;
import chess.ChessGameImpl;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ChessGameAdapter implements JsonDeserializer<ChessGame> {
    @Override
    public ChessGame deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Gson().fromJson(jsonElement, ChessGameImpl.class);
    }
}
