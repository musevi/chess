package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends ChessPieceImpl {

    private int counter;

    public Bishop(ChessGame.TeamColor color, PieceType type) {
        super(color, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();

        //RIGHTUP
        counter = 1;
        for(int i = myPosition.getColumn() + 1; i < 9; i++) {
            ChessPositionImpl newPosition = new ChessPositionImpl(i, myPosition.getRow() + counter);
            if(!isValidPosition(newPosition)) {continue;}
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, newPosition, null));
            counter++;
        }

        //LEFTUP
        counter = 1;
        for(int i = myPosition.getColumn() - 1; i > 0; i--) {
            ChessPositionImpl newPosition = new ChessPositionImpl(i, myPosition.getRow() + counter);
            if(!isValidPosition(newPosition)) {continue;}
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, newPosition, null));
            counter++;
        }

        //RIGHTDOWN
        counter = 1;
        for(int i = myPosition.getColumn() + 1; i < 9; i++) {
            ChessPositionImpl newPosition = new ChessPositionImpl(i, myPosition.getRow() - counter);
            if(!isValidPosition(newPosition)) {continue;}
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, newPosition, null));
            counter++;
        }

        //LEFTDOWN
        counter = 1;
        for(int i = myPosition.getColumn() - 1; i > 0; i--) {
            ChessPositionImpl newPosition = new ChessPositionImpl(i, myPosition.getRow() - counter);
            if(!isValidPosition(newPosition)) {continue;}
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, newPosition, null));
            counter++;
        }

//        System.out.println(moves.size());
//        for(ChessMove move : moves) {
//            System.out.println("Col: " + move.getEndPosition().getColumn() + " Row: " + move.getEndPosition().getRow());
//        }
        return moves;
    }

    private boolean isValidPosition(ChessPosition position) {
        if(position.getColumn() > 8 || position.getColumn() < 1) {
            return false;
        }
        if (position.getRow() > 8 || position.getRow() < 1) {
            return false;
        }
        return true;
    }
}
