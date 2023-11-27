import Services.ClearAppService;
import org.junit.jupiter.api.*;
import Requests.*;
import Results.*;
import Services.*;

import java.util.Optional;

public class ServiceTests {

    @BeforeAll
    public static void init() {
        ClearAppService clearAppService = new ClearAppService();
        clearAppService.clearApp();
    }

    @BeforeEach
    public void setup() {
        ClearAppService clearAppService = new ClearAppService();
        clearAppService.clearApp();
    }

    @Test
    @Order(1)
    @DisplayName("Successful Login Service Test")
    public void successfulLogin() {
        RegisterRequest registerRequest = new RegisterRequest("fakeuser", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        LoginRequest loginRequest = new LoginRequest("fakeuser", "fakepassword");
        LoginResult loginResult = new LoginService().login(loginRequest);

        Assertions.assertEquals(registerResult.getUsername(), loginResult.getUsername(), "usernames don't match");
        System.out.println(loginResult.getAuthToken());
        Assertions.assertNotEquals(null, loginResult.getAuthToken(), "AuthToken not returned");
    }

    @Test
    @Order(2)
    @DisplayName("Unsuccessful Login Service Test")
    public void unsuccessfulLogin() {
        RegisterRequest registerRequest = new RegisterRequest("fakeuser", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        LoginRequest loginRequest = new LoginRequest("realuser", "realpassword");
        LoginResult loginResult = new LoginService().login(loginRequest);

        Assertions.assertEquals("Error: unauthorized", loginResult.getMessage(), "Wrong error message returned");
    }

    @Test
    @Order(3)
    @DisplayName("Successful Registration")
    public void successfulRegistration() {
        RegisterRequest registerRequest = new RegisterRequest("fakeuser", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        Assertions.assertEquals("fakeuser", registerResult.getUsername(), "Wrong username returned");
        Assertions.assertNotEquals(null, registerResult.getAuthToken(), "No Authtoken returned");
    }

    @Test
    @Order(4)
    @DisplayName("Unsuccessful Registration")
    public void unsuccessfulRegistration() {
        RegisterRequest registerRequest = new RegisterRequest(null, "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        Assertions.assertEquals("Error: bad request", registerResult.getMessage(), "Wrong error message returned");
    }

    @Test
    @Order(5)
    @DisplayName("Successful Logout")
    public void successfulLogout() {
        RegisterRequest registerRequest = new RegisterRequest("fakeusername", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        LogoutResult logoutResult = new LogoutService().logout(registerResult.getAuthToken());

        Assertions.assertEquals(null, logoutResult.getMessage(), "Falsely returned error message");
    }

    @Test
    @Order(6)
    @DisplayName("Unsuccessful Logout")
    public void unsuccessfulLogout() {
        RegisterRequest registerRequest = new RegisterRequest("fakeusername", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        LogoutResult logoutResult = new LogoutService().logout("");

        Assertions.assertEquals("Error: unauthorized", logoutResult.getMessage(), "Didn't throw unauthorized message");
    }

    @Test
    @Order(7)
    @DisplayName("Successful Creation")
    public void successfulCreation() {
        RegisterRequest registerRequest = new RegisterRequest("fakeusername", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("A new game");
        CreateGameResult createGameResult = new CreateGameService().createGame(createGameRequest, registerResult.getAuthToken());

        Assertions.assertNotEquals(Optional.ofNullable(null), createGameResult.getGameID(), "No game ID returned");
    }

    @Test
    @Order(8)
    @DisplayName("Unsuccessful Creation")
    public void unsuccessfulCreation() {
        RegisterRequest registerRequest = new RegisterRequest("fakeusername", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("A new game");
        CreateGameResult createGameResult = new CreateGameService().createGame(createGameRequest, null);

        Assertions.assertEquals("Error: unauthorized", createGameResult.getMessage(), "Wrong message returned");
    }

    @Test
    @Order(9)
    @DisplayName("Successful Join")
    public void successfulJoin() {
        RegisterRequest registerRequest = new RegisterRequest("fakeusername", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("A new game");
        CreateGameResult createGameResult = new CreateGameService().createGame(createGameRequest, registerResult.getAuthToken());

        JoinGameRequest joinGameRequest = new JoinGameRequest("WHITE", createGameResult.getGameID());
        JoinGameResult joinGameResult = new JoinGameService().joinGame(joinGameRequest, registerResult.getAuthToken());

        Assertions.assertEquals(null, joinGameResult.getMessage(), "Falsely returned error message");

    }

    @Test
    @Order(10)
    @DisplayName("Unsuccessful Join")
    public void unsuccessfulJoin() {
        RegisterRequest registerRequest = new RegisterRequest("fakeusername", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("A new game");
        CreateGameResult createGameResult = new CreateGameService().createGame(createGameRequest, registerResult.getAuthToken());

        JoinGameRequest joinGameRequest = new JoinGameRequest("WHITE", null);
        JoinGameResult joinGameResult = new JoinGameService().joinGame(joinGameRequest, registerResult.getAuthToken());

        Assertions.assertEquals("Error: bad request", joinGameResult.getMessage(), "Wrong error message returned");
    }

    @Test
    @Order(11)
    @DisplayName("Successful List Games")
    public void successfulListGames() {
        RegisterRequest registerRequest = new RegisterRequest("fakeusername", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("A new game");
        CreateGameResult createGameResult = new CreateGameService().createGame(createGameRequest, registerResult.getAuthToken());

        JoinGameRequest joinGameRequest = new JoinGameRequest("WHITE", createGameResult.getGameID());
        JoinGameResult joinGameResult = new JoinGameService().joinGame(joinGameRequest, registerResult.getAuthToken());

        ListGamesResult listGamesResult = new ListGamesService().listGames(registerResult.getAuthToken());

        Assertions.assertEquals(null, listGamesResult.getMessage(), "Falsely returned error message");
    }

    @Test
    @Order(12)
    @DisplayName("Unsuccessful List Games")
    public void unsuccessfulListGames() {
        RegisterRequest registerRequest = new RegisterRequest("fakeusername", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("A new game");
        CreateGameResult createGameResult = new CreateGameService().createGame(createGameRequest, registerResult.getAuthToken());

        JoinGameRequest joinGameRequest = new JoinGameRequest("WHITE", createGameResult.getGameID());
        JoinGameResult joinGameResult = new JoinGameService().joinGame(joinGameRequest, registerResult.getAuthToken());

        ListGamesResult listGamesResult = new ListGamesService().listGames(null);

        Assertions.assertEquals("Error: unauthorized", listGamesResult.getMessage(), "Didn't return correct error");
    }

    @Test
    @Order(13)
    @DisplayName("Clear Test")
    public void clearTest() {
        RegisterRequest registerRequest = new RegisterRequest("fakeusername", "fakepassword", "fake@email.com");
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("A new game");
        CreateGameResult createGameResult = new CreateGameService().createGame(createGameRequest, registerResult.getAuthToken());

        JoinGameRequest joinGameRequest = new JoinGameRequest("WHITE", createGameResult.getGameID());
        JoinGameResult joinGameResult = new JoinGameService().joinGame(joinGameRequest, registerResult.getAuthToken());

        ListGamesResult listGamesResult = new ListGamesService().listGames(registerResult.getAuthToken());

        ClearAppResult clearAppResult = new ClearAppService().clearApp();

        Assertions.assertEquals(null, clearAppResult.getMessage(), "Falsely returned error message");
    }

}
