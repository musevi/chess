package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
//import static chess.ChessPiece.PieceType.QUEEN;

public class Pawn extends ChessPieceImpl {


    public Pawn(ChessGame.TeamColor color, ChessPiece.PieceType type) {
        super(color, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();
        if(this.getTeamColor() == ChessGame.TeamColor.WHITE) {
            ChessPosition newPosition = new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() + 1);
            if(board.getPiece(newPosition) != null) {
                return(captureMove(board, myPosition, moves));
            }
            if(isValidPosition(newPosition, board)) {
                moves.add(new ChessMoveImpl(myPosition, newPosition, null));
            }
            if(myPosition.getRow() == 2) {
                newPosition = new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() + 2);
                if(isValidPosition(newPosition, board) && board.getPiece(newPosition) == null) {
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
            }
            moves.addAll(captureMove(board, myPosition, moves));
            Set<ChessMove> promotionMoves = new HashSet<>();
            for(ChessMove move : moves) {
                if(move.getEndPosition().getRow() == 8) {
                    promotionMoves.add(new ChessMoveImpl(move.getStartPosition(), move.getEndPosition(), chess.ChessPiece.PieceType.QUEEN));
                    promotionMoves.add(new ChessMoveImpl(move.getStartPosition(), move.getEndPosition(), chess.ChessPiece.PieceType.ROOK));
                    promotionMoves.add(new ChessMoveImpl(move.getStartPosition(), move.getEndPosition(), chess.ChessPiece.PieceType.BISHOP));
                    promotionMoves.add(new ChessMoveImpl(move.getStartPosition(), move.getEndPosition(), chess.ChessPiece.PieceType.KNIGHT));
                }
            }
            moves.removeIf(move -> move.getEndPosition().getRow() == 8 && move.getPromotionPiece() == null);
            moves.addAll(promotionMoves);
            return moves;
        } else {
            ChessPosition newPosition = new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() - 1);
            if(board.getPiece(newPosition) != null) {
                return(captureMove(board, myPosition, moves));
            }
            if(isValidPosition(newPosition, board)) {
                moves.add(new ChessMoveImpl(myPosition, newPosition, null));
            }
            if(myPosition.getRow() == 7) {
                newPosition = new ChessPositionImpl(myPosition.getColumn(), myPosition.getRow() - 2);
                if(isValidPosition(newPosition, board) && board.getPiece(newPosition) == null) {
                    moves.add(new ChessMoveImpl(myPosition, newPosition, null));
                }
            }
            moves.addAll(captureMove(board, myPosition, moves));
            Set<ChessMove> promotionMoves = new HashSet<>();
            for(ChessMove move : moves) {
                if(move.getEndPosition().getRow() == 1) {
                    promotionMoves.add(new ChessMoveImpl(move.getStartPosition(), move.getEndPosition(), chess.ChessPiece.PieceType.QUEEN));
                    promotionMoves.add(new ChessMoveImpl(move.getStartPosition(), move.getEndPosition(), chess.ChessPiece.PieceType.ROOK));
                    promotionMoves.add(new ChessMoveImpl(move.getStartPosition(), move.getEndPosition(), chess.ChessPiece.PieceType.BISHOP));
                    promotionMoves.add(new ChessMoveImpl(move.getStartPosition(), move.getEndPosition(), chess.ChessPiece.PieceType.KNIGHT));
                }
            }
            moves.removeIf(move -> move.getEndPosition().getRow() == 1 && move.getPromotionPiece() == null);
            moves.addAll(promotionMoves);
            return moves;
        }
    }

    private Collection<ChessMove> captureMove(ChessBoard board, ChessPosition myPosition, Set<ChessMove> moves) {
        if(this.getTeamColor() == ChessGame.TeamColor.WHITE) {
            ChessPosition capturePosition1 = new ChessPositionImpl(myPosition.getColumn() + 1, myPosition.getRow() + 1);
            if(isValidCapture(capturePosition1, board)) {
                moves.add(new ChessMoveImpl(myPosition, capturePosition1, null));
            }
            ChessPosition capturePosition2 = new ChessPositionImpl(myPosition.getColumn() - 1, myPosition.getRow() + 1);
            if(isValidCapture(capturePosition2, board)) {
                moves.add(new ChessMoveImpl(myPosition, capturePosition2, null));
            }
            return moves;
        } else {
            ChessPosition capturePosition1 = new ChessPositionImpl(myPosition.getColumn() - 1, myPosition.getRow() - 1);
            if(isValidCapture(capturePosition1, board)) {
                moves.add(new ChessMoveImpl(myPosition, capturePosition1, null));
            }
            ChessPosition capturePosition2 = new ChessPositionImpl(myPosition.getColumn() + 1, myPosition.getRow() - 1);
            if(isValidCapture(capturePosition2, board)) {
                moves.add(new ChessMoveImpl(myPosition, capturePosition2, null));
            }
            return moves;
        }
    }

    private boolean isValidCapture(ChessPosition myPosition, ChessBoard board) {
        if(myPosition.getColumn() < 1 || myPosition.getColumn() > 8 || myPosition.getRow() < 1 || myPosition.getRow() > 8) {
            return false;
        }
        if(board.getPiece(myPosition) != null) {
            if(board.getPiece(myPosition).getTeamColor() != this.getTeamColor()) {
                return true;
            }
        }
        return false;
    }
}
