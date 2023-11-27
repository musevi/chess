package Models;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import com.google.gson.*;
import java.io.Reader;
import java.io.StringReader;

public class ModelSerializer {
    public static <T> T deserialize(String json, Class<T> responseClass) {
        return deserialize(new StringReader(json), responseClass);
    }

    public static <T> T deserialize(Reader reader, Class<T> responseClass) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ChessGame.class, new ChessGameAdapter());
        builder.registerTypeAdapter(ChessBoard.class, new ChessBoardAdapter());
        builder.registerTypeAdapter(ChessPiece.class, new ChessPieceAdapter());

        return builder.create().fromJson(reader, responseClass);
    }
}
