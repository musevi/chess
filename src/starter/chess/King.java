package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class King extends ChessPieceImpl {

    public King(ChessGame.TeamColor color, PieceType type) {
        super(color, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return super.pieceMoves(board, myPosition);
    }
}
