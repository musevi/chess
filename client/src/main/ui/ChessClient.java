package ui;

import Models.AuthToken;
import Models.Game;
import Requests.*;
import Results.*;
import exception.ResponseException;
import server.ServerFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ui.State.*;

public class ChessClient {
    private final ServerFacade server;
    private final String serverURL;
    private String authToken = null;
    private ArrayList<Game> currentGames = null;
    private State state = SIGNEDOUT;
    public ChessClient(String serverURL) {
        server = new ServerFacade(serverURL);
        this.serverURL = serverURL;
    }

    public String eval(String input) {
        try {
            var tokens = input.toLowerCase().split(" ");
            var cmd = (tokens.length > 0) ? tokens[0] : "help";
            var params = Arrays.copyOfRange(tokens, 1, tokens.length);
            return switch (cmd) {
                case "register" -> register(params);
                case "login" -> login(params);
                case "logout" -> logout();
                case "create" -> create(params);
                case "list" -> list();
                case "join" -> join(params);
                case "observe" -> observe(params);
                case "quit" -> quit();
                default -> help();
            };
        } catch (ResponseException e) {
            return e.getMessage();
        }
    }

    private String register(String... params) throws ResponseException {
        assertSignedOut();
        if(params.length != 3) {
            throw new ResponseException(400, "invalid registration format");
        }
        RegisterRequest registerRequest = new RegisterRequest(params[0], params[1], params[2]);
        RegisterResult registerResult = server.clientRegister(registerRequest);

        String username = registerResult.getUsername();
        authToken = registerResult.getAuthToken();
        state = SIGNEDIN;

        return String.format("New user created. Welcome, %s.\n\n" + help(), username);
    }

    private String login(String... params) throws ResponseException {
        assertSignedOut();
        if(params.length != 2) {
            throw new ResponseException(400, "invalid login format");
        }
        LoginRequest loginRequest = new LoginRequest(params[0], params[1]);
        LoginResult loginResult = server.clientLogin(loginRequest);

        String username = loginResult.getUsername();
        authToken = loginResult.getAuthToken();
        state = SIGNEDIN;

        return String.format("Logged in as %s.\n\n" + help(), username);
    }

    private String logout() throws ResponseException {
        assertSignedIn();

        LogoutResult logoutResult = server.clientLogout(authToken);
        state = SIGNEDOUT;
        authToken = null;
        return "You have logged out.\n\n" + help();
    }

    private String create(String... params) throws ResponseException {
        assertSignedIn();
        if(params.length != 1) {
            throw new ResponseException(400, "Game name must be one word");
        }
        CreateGameRequest createGameRequest = new CreateGameRequest(params[0]);
        CreateGameResult createGameResult = server.createGame(createGameRequest, authToken);

        String gameName = createGameResult.getGameName();
        return "Game created: " + gameName;
    }

    private String list() throws ResponseException {
        assertSignedIn();

        ListGamesResult listGamesResult = server.listGames(authToken);
        currentGames = (ArrayList<Game>) listGamesResult.getGames();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(Game game : currentGames) {
            String whiteUsername;
            String blackUsername;
            if(game.getWhiteUsername() != null) {
                whiteUsername = game.getWhiteUsername();
            } else {
                whiteUsername = "Not taken";
            }
            if(game.getBlackUsername() != null) {
                blackUsername = game.getBlackUsername();
            } else {
                blackUsername = "Not taken";
            }
            String s = i + " " + game.getGameName() + ": \n\t"
                    + "White Player: " + whiteUsername + "\n\t"
                    + "Black Player: " + blackUsername + "\n\n";
            sb.append(s);
            i++;
        }

        return sb.toString();
    }

    private String join(String... params) throws ResponseException {
        assertSignedIn();
        if(params.length != 2) {
            throw new ResponseException(400, "invalid join request format");
        }
        int gameToJoin = Integer.parseInt(params[0]) - 1;
        if(gameToJoin < 0 || gameToJoin > currentGames.size()) {
            throw new ResponseException(400, "invalid game id");
        }
        int gameID = currentGames.get(gameToJoin).getGameID();

        JoinGameRequest joinGameRequest = new JoinGameRequest(params[1], gameID);
        JoinGameResult joinGameResult = server.joinGame(joinGameRequest, authToken);

        BoardUI board = new BoardUI();
        board.printBoard();
        return "Game joined";
    }

    private String observe(String... params) throws ResponseException {
        assertSignedIn();
        if(params.length != 1) {
            throw new ResponseException(400, "invalid join request");
        }
        int gameToObserve = Integer.parseInt(params[0]) - 1;
        if(gameToObserve < 0 || gameToObserve > currentGames.size()) {
            throw new ResponseException(400, "invalid game id");
        }
        int gameID = currentGames.get(gameToObserve).getGameID();

        JoinGameRequest joinGameRequest = new JoinGameRequest(null, gameID);
        JoinGameResult joinGameResult = server.joinGame(joinGameRequest, authToken);

        BoardUI board = new BoardUI();
        board.printBoard();
        return "Observing game";
    }

    private String quit() throws ResponseException {
        if(state == SIGNEDIN) {
            logout();
            return "quit";
        }
        return "quit";
    }

    public String help() {
        if (state == SIGNEDOUT) {
            return "register <USERNAME> <PASSWORD> <EMAIL> - to create account\n" +
                    "login <USERNAME> <PASSWORD> - to play chess\n" +
                    "quit - to quit chess\n" +
                    "help - show commands";
        }
        return "create <GAME NAME> - create a game\n" +
                "list - list active games\n" +
                "join <ID> [WHITE | BLACK]\n" +
                "observe <ID> - a game\n" +
                "logout - logout of session\n" +
                "quit - to quit chess\n" +
                "help - show commands";
    }

    private void assertSignedOut() throws ResponseException {
        if (state != SIGNEDOUT) {
            throw new ResponseException(401, "You are currently signed in");
        }
    }

    private void assertSignedIn() throws ResponseException {
        if(state != SIGNEDIN) {
            throw new ResponseException(401, "You must sign in");
        }
    }
}
