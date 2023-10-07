package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ChessPieceImpl implements ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType pieceType;

    public ChessPieceImpl(ChessGame.TeamColor color, ChessPiece.PieceType type) {
        pieceColor = color;
        pieceType = type;
    }
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return null;
    }
}
