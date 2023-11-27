package Models;

import chess.ChessPiece;
import chess.ChessPieceImpl;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ChessPieceAdapter implements JsonDeserializer<ChessPiece> {
    @Override
    public ChessPiece deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Gson().fromJson(jsonElement, ChessPieceImpl.class);
    }
}
