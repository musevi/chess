package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
public class Knight extends ChessPieceImpl {

    private int[][] knightMoves = { {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1} };

    public Knight(ChessGame.TeamColor color, PieceType type) {
        super(color, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();
        for(int i = 0; i < 8; i++) {
            moves.add(new ChessMoveImpl(myPosition, new ChessPositionImpl(myPosition.getColumn() + knightMoves[i][0], myPosition.getRow() + knightMoves[i][1]),null));
        }

        //Make sure moves don't extend beyond board borders
        moves.removeIf(move -> move.getEndPosition().getColumn() > 8 || move.getEndPosition().getRow() > 8);
        moves.removeIf(move -> move.getEndPosition().getColumn() < 1 || move.getEndPosition().getRow() < 1);

        //Make sure you can't capture enemy pieces
        moves.removeIf(move -> board.getPiece(move.getEndPosition()) != null && board.getPiece(move.getEndPosition()).getTeamColor() == this.getTeamColor());
        return moves;
    }
}
