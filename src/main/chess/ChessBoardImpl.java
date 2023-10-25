package chess;

import java.util.Arrays;

public class ChessBoardImpl implements ChessBoard {

    private ChessPiece[][] chessBoard = new ChessPiece[8][8];
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        chessBoard[position.getColumn() - 1][position.getRow() - 1] = piece;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        return chessBoard[position.getColumn() - 1][position.getRow() - 1];
    }

    @Override
    public void resetBoard() {
        //WHITE
        addPiece(new ChessPositionImpl(1, 1), new Rook(chess.ChessGame.TeamColor.WHITE, chess.ChessPiece.PieceType.ROOK));
        addPiece(new ChessPositionImpl(2, 1), new Knight(chess.ChessGame.TeamColor.WHITE, chess.ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPositionImpl(3, 1), new Bishop(chess.ChessGame.TeamColor.WHITE, chess.ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPositionImpl(4, 1), new Queen(chess.ChessGame.TeamColor.WHITE, chess.ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPositionImpl(5, 1), new King(chess.ChessGame.TeamColor.WHITE, chess.ChessPiece.PieceType.KING));
        addPiece(new ChessPositionImpl(6, 1), new Bishop(chess.ChessGame.TeamColor.WHITE, chess.ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPositionImpl(7, 1), new Knight(chess.ChessGame.TeamColor.WHITE, chess.ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPositionImpl(8, 1), new Rook(chess.ChessGame.TeamColor.WHITE, chess.ChessPiece.PieceType.ROOK));
        for(int i = 1; i < 9; i++) {
            addPiece(new ChessPositionImpl(i, 2), new Pawn(chess.ChessGame.TeamColor.WHITE, chess.ChessPiece.PieceType.PAWN));
        }

        //BLACK
        addPiece(new ChessPositionImpl(1, 8), new Rook(chess.ChessGame.TeamColor.BLACK, chess.ChessPiece.PieceType.ROOK));
        addPiece(new ChessPositionImpl(2, 8), new Knight(chess.ChessGame.TeamColor.BLACK, chess.ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPositionImpl(3, 8), new Bishop(chess.ChessGame.TeamColor.BLACK, chess.ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPositionImpl(4, 8), new Queen(chess.ChessGame.TeamColor.BLACK, chess.ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPositionImpl(5, 8), new King(chess.ChessGame.TeamColor.BLACK, chess.ChessPiece.PieceType.KING));
        addPiece(new ChessPositionImpl(6, 8), new Bishop(chess.ChessGame.TeamColor.BLACK, chess.ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPositionImpl(7, 8), new Knight(chess.ChessGame.TeamColor.BLACK, chess.ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPositionImpl(8, 8), new Rook(chess.ChessGame.TeamColor.BLACK, chess.ChessPiece.PieceType.ROOK));
        for(int i = 1; i < 9; i++) {
            addPiece(new ChessPositionImpl(i, 7), new Pawn(chess.ChessGame.TeamColor.BLACK, chess.ChessPiece.PieceType.PAWN));
        }
    }

    public ChessPosition findKing(ChessGame.TeamColor teamColor) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                ChessPositionImpl position = new ChessPositionImpl(i + 1, j + 1);
                if(getPiece(position) == null) {continue;}
                if(getPiece(position).getTeamColor() == teamColor && getPiece(position).getPieceType() == ChessPiece.PieceType.KING) {
                    return position;
                }
            }
        }
        return null;
    }

    public ChessBoard copyBoard() {
        ChessBoard newBoard = new ChessBoardImpl();
        for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {
                ChessPosition position = new ChessPositionImpl(i, j);
                if(getPiece(position) == null) {continue;}
                ChessGame.TeamColor teamColor = getPiece(position).getTeamColor();
                ChessPiece.PieceType pieceType = getPiece(position).getPieceType();
                if(getPiece(position) != null) {
                    switch(getPiece(position).getPieceType()) {
                        case PAWN -> newBoard.addPiece(position, new Pawn(teamColor, pieceType));
                        case ROOK -> newBoard.addPiece(position, new Rook(teamColor, pieceType));
                        case KNIGHT -> newBoard.addPiece(position, new Knight(teamColor, pieceType));
                        case KING -> newBoard.addPiece(position, new King(teamColor, pieceType));
                        case QUEEN -> newBoard.addPiece(position, new Queen(teamColor, pieceType));
                        case BISHOP -> newBoard.addPiece(position, new Bishop(teamColor, pieceType));
                    }
                }
            }
        }
        return newBoard;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 7; i >= 0; i--) {
            for(int j = 0; j < 8; j++) {
                if(chessBoard[i][j] != null) {
                    sb.append("P");
                } else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
