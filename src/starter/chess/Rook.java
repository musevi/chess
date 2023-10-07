package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Rook extends ChessPieceImpl {
    public Rook(ChessGame.TeamColor color, PieceType type) {
        super(color, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();

        //MOVE UP
        for(int i = myPosition.getRow() + 1; i < 9; i++) {
            ChessPositionImpl newPosition = new ChessPositionImpl(myPosition.getColumn(), i);
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()){
                    moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(myPosition.getColumn(), i), null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(myPosition.getColumn(), i), null));
        }

        //MOVE DOWN
        for(int i = myPosition.getRow() - 1; i > 0; i--) {
            ChessPositionImpl newPosition = new ChessPositionImpl(myPosition.getColumn(), i);
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(myPosition.getColumn(), i), null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(myPosition.getColumn(), i), null));
        }

        //MOVE RIGHT
        for(int i = myPosition.getColumn() + 1; i < 9; i++) {
            ChessPositionImpl newPosition = new ChessPositionImpl(i, myPosition.getRow());
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(i, myPosition.getRow()), null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(i, myPosition.getRow()), null));
        }

        //MOVE LEFT
        for(int i = myPosition.getColumn() - 1; i > 0; i--) {
            ChessPositionImpl newPosition = new ChessPositionImpl(i, myPosition.getRow());
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(i, myPosition.getRow()), null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(i, myPosition.getRow()), null));
        }
        return moves;
    }
}
