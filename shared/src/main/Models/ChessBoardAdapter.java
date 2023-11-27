package Models;

import chess.ChessBoard;
import chess.ChessBoardImpl;
import chess.ChessGameImpl;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ChessBoardAdapter implements JsonDeserializer<ChessBoard> {
    @Override
    public ChessBoard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        //return new Gson().fromJson(jsonElement, ChessBoardImpl.class);
        return jsonDeserializationContext.deserialize(jsonElement, ChessBoardImpl.class);
    }
}
