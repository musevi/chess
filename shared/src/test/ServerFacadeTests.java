import Requests.*;
import Results.*;
import exception.ResponseException;
import org.junit.jupiter.api.*;
import server.ServerFacade;
import Models.Game;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServerFacadeTests {
    @BeforeEach
    public void setup() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");
        server.clearApp();
    }

    @Test
    @Order(1)
    @DisplayName("Successful register test")
    public void successfulRegister() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest request = new RegisterRequest("username", "password", "email");
        RegisterResult result = server.clientRegister(request);

        Assertions.assertEquals("username", result.getUsername(), "Returned username doesn't match the request");
        Assertions.assertNotEquals(null, result.getAuthToken(), "Didn't return authtoken");
    }

    @Test
    @Order(2)
    @DisplayName("Unsuccessful register test")
    public void unsuccessfulRegister() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest request1 = new RegisterRequest("username", "password", "email");
        RegisterResult result1 = server.clientRegister(request1);

        //Duplicate username
        RegisterRequest request2 = new RegisterRequest("username", "password1", "email1");
        assertThrows(ResponseException.class, () -> server.clientRegister(request2));

    }

    @Test
    @Order(3)
    @DisplayName("Successful login test")
    public void successfulLogin() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        LoginRequest loginRequest = new LoginRequest("username", "password");
        LoginResult loginResult = server.clientLogin(loginRequest);

        Assertions.assertEquals("username", loginResult.getUsername(), "Logged in username does not match request");
        Assertions.assertNotEquals(null, loginResult.getAuthToken(), "authtoken not returned");
    }

    @Test
    @Order(4)
    @DisplayName("Unsuccessful login test")
    public void unsuccessfulLogin() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        //Log in non-existing user
        LoginRequest loginRequest = new LoginRequest("username", "password");
        assertThrows(ResponseException.class, () -> server.clientLogin(loginRequest));
    }

    @Test
    @Order(5)
    @DisplayName("Successful logout test")
    public void successfulLogout() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        LogoutResult logoutResult = server.clientLogout(registerResult.getAuthToken());

        //Make illegal request when logged out
        assertThrows(ResponseException.class, () -> server.listGames(registerResult.getAuthToken()));
    }

    @Test
    @Order(6)
    @DisplayName("Unsuccessful logout test")
    public void unsuccessfulLogout() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        //Log out random authtoken
        assertThrows(ResponseException.class, () -> server.clientLogout(UUID.randomUUID().toString()));
    }

    @Test
    @Order(7)
    @DisplayName("Successful create game test")
    public void successfulCreateGame() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("NewGame");
        CreateGameResult createGameResult = server.createGame(createGameRequest, registerResult.getAuthToken());

        Assertions.assertEquals(createGameRequest.getGameName(), createGameResult.getGameName(), "Returned wrong game");
    }

    @Test
    @Order(8)
    @DisplayName("Unsuccessful create game test")
    public void unsuccessfulCreateGame() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("NewGame");

        //Bad authToken
        assertThrows(ResponseException.class, () -> server.createGame(createGameRequest, UUID.randomUUID().toString()));
    }

    @Test
    @Order(9)
    @DisplayName("Successful list games test")
    public void successfulListGames() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("Game1");
        CreateGameResult createGameResult = server.createGame(createGameRequest, registerResult.getAuthToken());
        CreateGameRequest createGameRequest2 = new CreateGameRequest("Game2");
        CreateGameResult createGameResult2 = server.createGame(createGameRequest2, registerResult.getAuthToken());

        List<Game> games = server.listGames(registerResult.getAuthToken()).getGames();

        Assertions.assertEquals(2, games.size(), "Returned wrong number of games");
    }

    @Test
    @Order(10)
    @DisplayName("Unsuccessful list games test")
    public void unsuccessfulListGames() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("Game1");
        CreateGameResult createGameResult = server.createGame(createGameRequest, registerResult.getAuthToken());
        CreateGameRequest createGameRequest2 = new CreateGameRequest("Game2");
        CreateGameResult createGameResult2 = server.createGame(createGameRequest2, registerResult.getAuthToken());

        //Request with bad authToken
        assertThrows(ResponseException.class, () -> server.listGames(UUID.randomUUID().toString()));
    }

    @Test
    @Order(11)
    @DisplayName("Successful join game test")
    public void successfulJoinGameTest() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("NewGame");
        CreateGameResult createGameResult = server.createGame(createGameRequest, registerResult.getAuthToken());

        List<Game> games = server.listGames(registerResult.getAuthToken()).getGames();

        int gameID = games.get(0).getGameID();

        JoinGameRequest joinGameRequest = new JoinGameRequest("white", gameID);
        JoinGameResult joinGameResult = server.joinGame(joinGameRequest, registerResult.getAuthToken());

        games = server.listGames(registerResult.getAuthToken()).getGames();
        Assertions.assertEquals(registerResult.getUsername(), games.get(0).getWhiteUsername(), "Username not added to game");
    }

    @Test
    @Order(12)
    @DisplayName("Unsuccessful join game test")
    public void unsuccessfulJoinGame() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("NewGame");
        CreateGameResult createGameResult = server.createGame(createGameRequest, registerResult.getAuthToken());

        List<Game> games = server.listGames(registerResult.getAuthToken()).getGames();

        //Try to join with bad game ID
        JoinGameRequest joinGameRequest = new JoinGameRequest("white", 0);
        assertThrows(ResponseException.class, () -> server.joinGame(joinGameRequest, registerResult.getAuthToken()));
    }

    @Test
    @Order(13)
    @DisplayName("Clear test")
    public void clearTest() throws Exception {
        ServerFacade server = new ServerFacade("http://localhost:8080");

        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email");
        RegisterResult registerResult = server.clientRegister(registerRequest);

        server.clearApp();

        LoginRequest loginRequest = new LoginRequest("username", "password");

        assertThrows(ResponseException.class, () -> server.clientLogin(loginRequest));
    }
}
