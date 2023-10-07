package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends ChessPieceImpl {


    public Pawn(ChessGame.TeamColor color, ChessPiece.PieceType type) {
        super(color, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();
        if(this.getTeamColor() == ChessGame.TeamColor.WHITE) {
            moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() + 1), null));
            if(myPosition.getRow() == 2) {
                moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() + 2), null));
            }
            return moves;
        } else {
            moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() - 1), null));
            if(myPosition.getRow() == 7) {
                moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() - 2), null));
            }
            return moves;
        }
        //return null;
    }
}
