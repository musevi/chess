package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Queen extends ChessPieceImpl {

    private int counter;

    public Queen(ChessGame.TeamColor color, PieceType type) {
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
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, newPosition, null));
        }

        //MOVE DOWN
        for(int i = myPosition.getRow() - 1; i > 0; i--) {
            ChessPositionImpl newPosition = new ChessPositionImpl(myPosition.getColumn(), i);
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, newPosition, null));
        }

        //MOVE RIGHT
        for(int i = myPosition.getColumn() + 1; i < 9; i++) {
            ChessPositionImpl newPosition = new ChessPositionImpl(i, myPosition.getRow());
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, newPosition, null));
        }

        //MOVE LEFT
        for(int i = myPosition.getColumn() - 1; i > 0; i--) {
            ChessPositionImpl newPosition = new ChessPositionImpl(i, myPosition.getRow());
            if(board.getPiece(newPosition) != null) {
                if(board.getPiece(newPosition).getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
                break;
            }
            moves.add(new ChessMoveImpl(myPosition, newPosition, null));
        }

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
