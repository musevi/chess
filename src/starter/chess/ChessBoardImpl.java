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
        for(int i = 0; i < 8; i++) {
            Arrays.fill(chessBoard[i], null);
        }
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
