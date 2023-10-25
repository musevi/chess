package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ChessGameImpl implements ChessGame {

    private TeamColor teamTurn;
    private ChessBoard gameBoard;

    public ChessGameImpl() {
        teamTurn = TeamColor.WHITE;
        gameBoard = new ChessBoardImpl();
        gameBoard.resetBoard();
    }

    public ChessGameImpl(ChessBoard board, TeamColor color) {
        teamTurn = color;
        gameBoard = board;
    }
    @Override
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        //return gameBoard.getPiece(startPosition).pieceMoves(gameBoard, startPosition);
        Set<ChessMove> toRemove = new HashSet<>();
        Set<ChessMove> totalMoves = (Set<ChessMove>) gameBoard.getPiece(startPosition).pieceMoves(gameBoard, startPosition);
        for (ChessMove move : totalMoves) {
            ChessBoard newBoard = gameBoard.copyBoard();
            ChessGame newGame = new ChessGameImpl(newBoard, newBoard.getPiece(startPosition).getTeamColor());
            try {
                newGame.makeMove(move);
//                if(newGame.isInCheck(teamTurn)) {
//                    return false;
//                }
            } catch (Exception e){
                toRemove.add(move);
                //return false;
            }
        }
        totalMoves.removeAll(toRemove);
        return totalMoves;
    }

    private Collection<ChessMove> goodMoves(ChessPosition startPosition) {
        return gameBoard.getPiece(startPosition).pieceMoves(gameBoard, startPosition);
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if(gameBoard.getPiece(move.getStartPosition()) == null) {
            throw new InvalidMoveException("No piece in start position");
        }
        if(gameBoard.getPiece(move.getStartPosition()).getTeamColor() != teamTurn) {
            throw new InvalidMoveException("Can't move other team's piece");
        }

        ChessPiece pieceToMove = gameBoard.getPiece(move.getStartPosition());
        Set<ChessMove> validMoves;
        validMoves = new HashSet<>(gameBoard.getPiece(move.getStartPosition()).pieceMoves(gameBoard, move.getStartPosition()));
        if(validMoves.contains(move)) {
            gameBoard.addPiece(move.getStartPosition(), null);
            gameBoard.addPiece(move.getEndPosition(), pieceToMove);
            if(move.getPromotionPiece() != null) {
                handlePromotion(move);
            }
        } else {
            throw new InvalidMoveException("Invalid move for piece");
        }
        if(isInCheck(teamTurn)) {
            gameBoard.addPiece(move.getEndPosition(), null);
            gameBoard.addPiece(move.getStartPosition(), pieceToMove);
            throw new InvalidMoveException("You are in check, fool");
        }
        changeTurn();
    }

    private void handlePromotion(ChessMove move) {
        switch(move.getPromotionPiece()) {
            case QUEEN -> gameBoard.addPiece(move.getEndPosition(), new Queen(teamTurn, ChessPiece.PieceType.QUEEN));
            case ROOK -> gameBoard.addPiece(move.getEndPosition(), new Rook(teamTurn, ChessPiece.PieceType.ROOK));
            case BISHOP -> gameBoard.addPiece(move.getEndPosition(), new Bishop(teamTurn, ChessPiece.PieceType.BISHOP));
            case KNIGHT -> gameBoard.addPiece(move.getEndPosition(), new Knight(teamTurn, ChessPiece.PieceType.KNIGHT));
        }
    }

    private Set<ChessMove> removeBadMoves (ChessBoard board, Set<ChessMove> moveList) {
        Set<ChessMove> toRemove = new HashSet<>();
        for(ChessMove hypoMove : moveList) {
            ChessBoard newBoard = board.copyBoard();
            ChessGame newGame = new ChessGameImpl(newBoard, teamTurn);
            try {
                newGame.makeMove(hypoMove);
//                if(newGame.isInCheck(teamTurn)) {
//                    return false;
//                }
            } catch (Exception e){
                toRemove.add(hypoMove);
                //System.out.println(e);
                //return false;
            }
        }
        moveList.removeAll(toRemove);
        return moveList;
    }


    @Override
    public boolean isInCheck(TeamColor teamColor) {
        TeamColor movingTeam;
        if(teamColor == TeamColor.WHITE) {
            movingTeam = TeamColor.BLACK;
        } else {
            movingTeam = TeamColor.WHITE;
        }
        ChessPosition kingPos = gameBoard.findKing(teamColor);

        Set<ChessMove> teamMoves = new HashSet<>();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                ChessPositionImpl position = new ChessPositionImpl(i + 1, j + 1);
                if(gameBoard.getPiece(position) == null) {continue;}
                if(gameBoard.getPiece(position).getTeamColor() == movingTeam) {
                    teamMoves.addAll(goodMoves(position));
                }
            }
        }
        for(ChessMove move : teamMoves) {
            if(move.getEndPosition().equals(kingPos)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        if(!isInCheck(teamColor)) {
            return false;
        }
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessPosition position = new ChessPositionImpl(i, j);
                if(gameBoard.getPiece(position) == null) {continue;}
                if(gameBoard.getPiece(position).getTeamColor() == teamColor) {
                    Set<ChessMove> validMoves;
                    validMoves = new HashSet<>(gameBoard.getPiece(position).pieceMoves(gameBoard, position));
                    for (ChessMove move : validMoves) {
                        ChessBoard newBoard = gameBoard.copyBoard();
                        ChessGame newGame = new ChessGameImpl(newBoard, teamColor);
                        try {
                            newGame.makeMove(move);
                            if(!newGame.isInCheck(teamColor)) {
                                return false;
                            }
                        } catch (Exception e){
                            //System.out.println(e);
                            //return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        if(isInCheck(teamColor)) {
            return false;
        }
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessPosition position = new ChessPositionImpl(i, j);
                if(gameBoard.getPiece(position) == null) {continue;}
                if(gameBoard.getPiece(position).getTeamColor() == teamColor) {
                    Set<ChessMove> validMoves;
                    validMoves = new HashSet<>(gameBoard.getPiece(position).pieceMoves(gameBoard, position));
                    for (ChessMove move : validMoves) {
                        ChessBoard newBoard = gameBoard.copyBoard();
                        ChessGame newGame = new ChessGameImpl(newBoard, teamColor);
                        try {
                            newGame.makeMove(move);
                            if(!newGame.isInCheck(teamColor)) {
                                return false;
                            }
                        } catch (Exception e){
                            //System.out.println(e);
                            //return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void setBoard(ChessBoard board) {
        gameBoard = board;
    }

    @Override
    public ChessBoard getBoard() {
        return gameBoard;
    }

    private void changeTurn() {
        if(teamTurn == TeamColor.WHITE) {
            teamTurn = TeamColor.BLACK;
        } else {
            teamTurn = TeamColor.WHITE;
        }
    }
}
