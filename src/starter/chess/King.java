package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class King extends ChessPieceImpl {

    public King(ChessGame.TeamColor color, PieceType type) {
        super(color, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();

        ChessPosition up = new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() + 1);
        if(isValidPosition(up, board)) {
            moves.add(new ChessMoveImpl(myPosition, up, null));
        }
        ChessPosition down = new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() - 1);
        if(isValidPosition(down, board)) {
            moves.add(new ChessMoveImpl(myPosition, down, null));
        }
        ChessPosition right = new ChessPositionImpl(myPosition.getColumn() + 1, myPosition.getRow());
        if(isValidPosition(right, board)) {
            moves.add(new ChessMoveImpl(myPosition, right, null));
        }
        ChessPosition left = new ChessPositionImpl(myPosition.getColumn() - 1, myPosition.getRow());
        if(isValidPosition(left, board)) {
            moves.add(new ChessMoveImpl(myPosition, left, null));
        }

        ChessPosition upRight = new ChessPositionImpl(myPosition.getColumn() + 1, myPosition.getRow() + 1);
        if(isValidPosition(upRight, board)) {
            moves.add(new ChessMoveImpl(myPosition, upRight, null));
        }
        ChessPosition upLeft = new ChessPositionImpl(myPosition.getColumn() + 1, myPosition.getRow() - 1);
        if(isValidPosition(upLeft, board)) {
            moves.add(new ChessMoveImpl(myPosition, upLeft, null));
        }
        ChessPositionImpl downRight = new ChessPositionImpl(myPosition.getColumn() - 1, myPosition.getRow() + 1);
        if(isValidPosition(downRight, board)) {
            moves.add(new ChessMoveImpl(myPosition, downRight, null));
        }
        ChessPositionImpl downLeft = new ChessPositionImpl(myPosition.getColumn() - 1, myPosition.getRow() - 1);
        if(isValidPosition(downLeft, board)) {
            moves.add(new ChessMoveImpl(myPosition, downLeft, null));
        }

        return moves;
    }

}
