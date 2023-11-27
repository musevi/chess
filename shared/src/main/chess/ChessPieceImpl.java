package chess;

import java.util.Collection;

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

    @Override
    public boolean isValidPosition(ChessPosition position, ChessBoard board) {
        if(position.getColumn() > 8 || position.getColumn() < 1) {
            return false;
        }
        if (position.getRow() > 8 || position.getRow() < 1) {
            return false;
        }
        if(board.getPiece(position) != null) {
            if(board.getPiece(position).getTeamColor() == this.getTeamColor()) {
                return false;
            }
        }
        return true;
    }
}
