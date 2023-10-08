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
            ChessPosition newPosition = new ChessPositionImpl(myPosition.getColumn() + knightMoves[i][0], myPosition.getRow() + knightMoves[i][1]);
            if(isValidPosition(newPosition, board)) {
                moves.add(new ChessMoveImpl(myPosition, newPosition, null));
            }
        }

        return moves;
    }
}
