package chess;

import java.util.Objects;

public class ChessMoveImpl implements ChessMove {

    private ChessPosition startPosition;
    private ChessPosition endPosition;
    private ChessPiece.PieceType upgradePiece;

    public ChessMoveImpl(ChessPosition start, ChessPosition end, ChessPiece.PieceType upgrade) {
        startPosition = start;
        endPosition = end;
        upgradePiece = upgrade;
    }


    @Override
    public ChessPosition getStartPosition() {
        return startPosition;
    }

    @Override
    public ChessPosition getEndPosition() {
        return endPosition;
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return upgradePiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMoveImpl chessMove = (ChessMoveImpl) o;
        return Objects.equals(startPosition, chessMove.startPosition) && Objects.equals(endPosition, chessMove.endPosition) && upgradePiece == chessMove.upgradePiece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPosition, endPosition, upgradePiece);
    }
}
