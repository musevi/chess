package ui;

import chess.ChessBoard;
import chess.ChessBoardImpl;
import chess.ChessPosition;
import chess.ChessPositionImpl;

import static chess.ChessGame.TeamColor.*;
import static ui.EscapeSequences.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class BoardUI {
    private ChessBoard board = new ChessBoardImpl();

    public BoardUI() {
        board.resetBoard();
    }

    public void printBoard() {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        for(int i = 9; i >= 0; i--) {
            for(int j = 0; j < 10; j++) {
                if(i == 0 || i == 9) {
                    out.print(SET_BG_COLOR_LIGHT_GREY);
                    if(j == 0 || j == 9) {
                        out.print("   ");
                    } else {
                        out.print(SET_TEXT_BOLD);
                        out.print(SET_TEXT_COLOR_BLACK);
                        out.print(" " + getCharForNumber(j) + " ");
                    }
                } else {
                    if(j == 0 || j == 9) {
                        out.print(SET_BG_COLOR_LIGHT_GREY);
                        out.print(SET_TEXT_BOLD);
                        out.print(SET_TEXT_COLOR_BLACK);
                        out.print(" " + i + " ");
                    } else {
                        if((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                            out.print(SET_BG_COLOR_BLACK);
                            out.print(SET_TEXT_BOLD);
                            determinePiece(out, i, j);
                        } else {
                            out.print(SET_BG_COLOR_WHITE);
                            out.print(SET_TEXT_BOLD);
                            determinePiece(out, i, j);
                        }
                    }
                }
            }
            out.print(RESET_BG_COLOR);
            out.print("\n");
        }

        out.print(RESET_BG_COLOR);
        out.print("\n");

        for(int i = 0; i < 10; i++) {
            for(int j = 9; j >= 0; j--) {
                if(i == 0 || i == 9) {
                    out.print(SET_BG_COLOR_LIGHT_GREY);
                    if(j == 0 || j == 9) {
                        out.print("   ");
                    } else {
                        out.print(SET_TEXT_BOLD);
                        out.print(SET_TEXT_COLOR_BLACK);
                        out.print(" " + getCharForNumber(j) + " ");
                    }
                } else {
                    if(j == 0 || j == 9) {
                        out.print(SET_BG_COLOR_LIGHT_GREY);
                        out.print(SET_TEXT_BOLD);
                        out.print(SET_TEXT_COLOR_BLACK);
                        out.print(" " + i + " ");
                    } else {
                        if((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                            out.print(SET_BG_COLOR_BLACK);
                            out.print(SET_TEXT_BOLD);
                            determinePiece(out, i, j);
                        } else {
                            out.print(SET_BG_COLOR_WHITE);
                            out.print(SET_TEXT_BOLD);
                            determinePiece(out, i, j);
                        }
                    }
                }
            }
            out.print(RESET_BG_COLOR);
            out.print("\n");
        }
    }

    private void determinePiece(PrintStream out, int i, int j) {
        ChessPosition pos = new ChessPositionImpl(j, i);
        if(board.getPiece(pos) == null) {
            out.print("   ");
            return;
        }
        if(board.getPiece(pos).getTeamColor() == WHITE) {
            out.print(SET_TEXT_COLOR_BLUE);
            printPiece(out, i, j);
        } else {
            out.print(SET_TEXT_COLOR_RED);
            printPiece(out, i, j);
        }
    }

    private void printPiece(PrintStream out, int i, int j) {
        ChessPosition pos = new ChessPositionImpl(j, i);
        switch(board.getPiece(pos).getPieceType()) {
            case PAWN:
                out.print(" P ");
                break;
            case ROOK:
                out.print(" R ");
                break;
            case BISHOP:
                out.print(" B ");
                break;
            case KNIGHT:
                out.print(" N ");
                break;
            case QUEEN:
                out.print(" Q ");
                break;
            case KING:
                out.print(" K ");
                break;
        }
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 'a' - 1)) : null;
    }


}
