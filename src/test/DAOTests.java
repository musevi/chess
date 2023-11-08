import DataAccess.AuthTokenDAO;
import DataAccess.DataAccessException;
import DataAccess.GameDAO;
import DataAccess.UserDAO;
import Models.AuthToken;
import Models.Game;
import Models.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class DAOTests {

    @BeforeEach
    public void setup() throws Exception {
        AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        GameDAO gameDAO = GameDAO.getInstance();
        authTokenDAO.clearAll();
        userDAO.clearAll();
        gameDAO.clear();
    }

    @Test
    @Order(1)
    @DisplayName("Successful Add AuthToken Test")
    public void successfulAddAuthToken() throws Exception {
        AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
        AuthToken authToken = new AuthToken("fakeuser");
        authTokenDAO.addAuthToken(authToken);

        AuthToken gottenToken = authTokenDAO.getAuthToken(authToken.getAuthToken());

        Assertions.assertEquals(authToken.getAuthToken(), gottenToken.getAuthToken(), "Tokens not equal");
    }

    @Test
    @Order(2)
    @DisplayName("Unsuccessful Add AuthToken Test")
    public void unsuccessfulAddAuthToken() throws Exception {
        AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
        AuthToken authToken = new AuthToken(null);

        assertThrows(DataAccessException.class, () -> authTokenDAO.addAuthToken(authToken));
    }

    @Test
    @Order(3)
    @DisplayName("Successful Get AuthToken Test")
    public void successfulGetAuthTokenTest() throws Exception {
        AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
        AuthToken authToken = new AuthToken("anotherfakeuser");
        authTokenDAO.addAuthToken(authToken);

        AuthToken gottenToken = authTokenDAO.getAuthToken(authToken.getAuthToken());

        Assertions.assertEquals(authToken.getAuthToken(), gottenToken.getAuthToken(), "Tokens not equal");
    }

    @Test
    @Order(4)
    @DisplayName("Unsuccessful Get AuthToken Test")
    public void unsuccessfulGetAuthTokenTest() throws Exception {
        AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
        AuthToken authToken = new AuthToken("anotherfakeuser");
        authTokenDAO.addAuthToken(authToken);

        assertThrows(DataAccessException.class, () -> authTokenDAO.getAuthToken(UUID.randomUUID().toString()));
    }

    @Test
    @Order(5)
    @DisplayName("Successful Delete AuthToken Test")
    public void successfulDeleteAuthTokenTest() throws Exception {
        AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
        AuthToken authToken = new AuthToken("fakeuser");
        authTokenDAO.addAuthToken(authToken);

        authTokenDAO.deleteAuthToken(authToken.getAuthToken());

        assertThrows(DataAccessException.class, () -> authTokenDAO.getAuthToken(authToken.getAuthToken()));
    }

    @Test
    @Order(6)
    @DisplayName("Unsuccessful Delete AuthToken Test")
    public void unsuccessfulDeleteAuthTokenTest() throws Exception {
        AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
        AuthToken authToken = new AuthToken("fakeuser");
        authTokenDAO.addAuthToken(authToken);

        authTokenDAO.deleteAuthToken(UUID.randomUUID().toString());

        AuthToken gottenToken = authTokenDAO.getAuthToken(authToken.getAuthToken());

        Assertions.assertEquals(authToken.getAuthToken(), gottenToken.getAuthToken(), "AuthToken was deleted");
    }

    @Test
    @Order(7)
    @DisplayName("Clear AuthTokens Test")
    public void clearAuthTokensTest() throws Exception {
        AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
        AuthToken authToken = new AuthToken("fakeuser");
        authTokenDAO.addAuthToken(authToken);
        AuthToken authToken1 = new AuthToken("anotherfakeuser");

        authTokenDAO.clearAll();

        assertThrows(DataAccessException.class, () -> authTokenDAO.getAuthToken(authToken.getAuthToken()));
        assertThrows(DataAccessException.class, () -> authTokenDAO.getAuthToken(authToken1.getAuthToken()));
    }

    @Test
    @Order(8)
    @DisplayName("Successful Add User Test")
    public void successfulAddUserTest() throws Exception {
        UserDAO userDAO = UserDAO.getInstance();
        User user = new User("fakeuser", "fakepassword", "fakeemail");
        userDAO.createUser(user);

        User gottenUser = userDAO.getUser(user.getUsername(), user.getPassword());

        Assertions.assertEquals(gottenUser.getUsername(), user.getUsername(), "Usernames do not match");
    }

    @Test
    @Order(9)
    @DisplayName("Unsuccessful Add User Test")
    public void unsuccessfulAddUserTest() throws Exception {
        UserDAO userDAO = UserDAO.getInstance();
        User user = new User(null, "fakepassword", "fakeemail");

        assertThrows(DataAccessException.class, () -> userDAO.createUser(user));
    }

    @Test
    @Order(10)
    @DisplayName("Successful Get User Test")
    public void successfulGetUserTest() throws Exception {
        UserDAO userDAO = UserDAO.getInstance();
        User user = new User("anotherfakeuser", "anotherfakepassword", "anotherfakeemail");
        userDAO.createUser(user);

        User gottenUser = userDAO.getUser(user.getUsername(), user.getPassword());

        Assertions.assertEquals(gottenUser.getUsername(), user.getUsername(), "Usernames do not match");
        Assertions.assertEquals(gottenUser.getPassword(), user.getPassword(), "Passwords do not match");
    }

    @Test
    @Order(11)
    @DisplayName("Unsuccessful Get User Test")
    public void unsuccessfulGetUserTest() throws Exception {
        UserDAO userDAO = UserDAO.getInstance();
        User user = new User("fakeuser", "fakepassword", "fakeemail");
        userDAO.createUser(user);

        assertThrows(DataAccessException.class, () -> userDAO.getUser("badusername", "badpassword"));
    }

    @Test
    @Order(12)
    @DisplayName("User Does Not Already Exist Test")
    public void userDoesNotAlreadyExistTest() throws Exception {
        UserDAO userDAO = UserDAO.getInstance();
        User user = new User("fakeuser", "fakepassword", "fakeemail");
        userDAO.createUser(user);

        User user1 = new User("differentuser", "differentpassword", "differentemail");
        userDAO.checkUserExists("differentuser");

        userDAO.createUser(user1);
        User gottenUser = userDAO.getUser("differentuser", "differentpassword");

        Assertions.assertEquals(gottenUser.getUsername(), user1.getUsername(), "Usernames do not match");
    }

    @Test
    @Order(13)
    @DisplayName("User Already Exists Test")
    public void userAlreadyExistsTest() throws Exception {
        UserDAO userDAO = UserDAO.getInstance();
        User user = new User("fakeuser", "fakepassword", "fakeemail");
        userDAO.createUser(user);

        User user1 = new User("fakeuser", "fakepassword", "fakeemail");

        assertThrows(DataAccessException.class, () -> userDAO.checkUserExists("fakeuser"));
    }

    @Test
    @Order(14)
    @DisplayName("Clear Users Test")
    public void clearUsersTest() throws Exception {
        UserDAO userDAO = UserDAO.getInstance();
        User user = new User("fakeuser", "fakepassword", "fakeemail");
        userDAO.createUser(user);
        User user1 = new User("anotherfakeuser", "anotherfakepassword", "antoherfakeemail");
        userDAO.createUser(user1);

        userDAO.clearAll();

        assertThrows(DataAccessException.class, () -> userDAO.getUser("fakeuser", "fakepassword"));
        assertThrows(DataAccessException.class, () -> userDAO.getUser("anotherfakeuser", "anotherfakepassword"));
    }

    @Test
    @Order(15)
    @DisplayName("Successful Insert Game Test")
    public void successfulInsertGameTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();

        int gameid = gameDAO.Insert("newgame");
        Game game = gameDAO.find(gameid);

        Assertions.assertEquals("newgame", game.getGameName(), "Returned wrong game name");
    }

    @Test
    @Order(16)
    @DisplayName("Unsuccessful Insert Game Test")
    public void unsuccessfulInsertGameTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();

        assertThrows(DataAccessException.class, () -> gameDAO.Insert(null));
    }

    @Test
    @Order(17)
    @DisplayName("Successful Find Game Test")
    public void successfulFindGameTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();

        int gameid = gameDAO.Insert("anothernewgame");
        Game game = gameDAO.find(gameid);

        Assertions.assertEquals("anothernewgame", game.getGameName(), "Returned wrong game name");
    }

    @Test
    @Order(18)
    @DisplayName("Unsuccessful Find Game Test")
    public void unsuccessfulFindGameTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();

        int gameid = gameDAO.Insert("anothernewgame");

        assertThrows(DataAccessException.class, () -> gameDAO.find(gameid - 1));
    }

    @Test
    @Order(19)
    @DisplayName("Succssful Claim Spot Test")
    public void successfulClaimSpotTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();
        int gameid = gameDAO.Insert("newgame");
        String username = "white player";

        gameDAO.claimSpot(username, gameDAO.find(gameid), "WHITE");

        Assertions.assertEquals(username, gameDAO.find(gameid).getWhiteUsername(), "Usernames did not match");
    }

    @Test
    @Order(20)
    @DisplayName("Unsuccessful Claim Spot Test")
    public void unsuccessfulClaimSpotTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();
        int gameid = gameDAO.Insert("newgame");
        String username = "black player";

        gameDAO.claimSpot(username, gameDAO.find(gameid), "BLcK");

        Assertions.assertNotEquals(username, gameDAO.find(gameid).getBlackUsername());
    }

    @Test
    @Order(21)
    @DisplayName("Game Exists Test")
    public void gameExistsTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();
        int gameid = gameDAO.Insert("newgame");

        boolean bool = gameDAO.gameExists(gameid);

        Assertions.assertTrue(bool);
    }

    @Test
    @Order(22)
    @DisplayName("Game Not Exists Test")
    public void gameNotExistsTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();

        boolean bool = gameDAO.gameExists(0);

        Assertions.assertFalse(bool);
    }

    @Test
    @Order(23)
    @DisplayName("Successful Find All Games Test")
    public void successfulFindAllGamesTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();
        int gameid1 = gameDAO.Insert("newgame1");
        int gameid2 = gameDAO.Insert("newgame2");
        int gameid3 = gameDAO.Insert("newgame3");

        String username1 = "white player";
        gameDAO.claimSpot(username1, gameDAO.find(gameid1), "WHITE");
        String username2 = "black player";
        gameDAO.claimSpot(username2, gameDAO.find(gameid2), "BLACK");
        String username3 = "Spectator";
        gameDAO.claimSpot(username3, gameDAO.find(gameid3), null);

        List<Game> gameList = new ArrayList<>();
        gameList = gameDAO.findAll();

        Assertions.assertEquals(3, gameList.size(), "Wrong number of games returned");
        Assertions.assertEquals(username1, gameDAO.find(gameid1).getWhiteUsername());
        Assertions.assertEquals(username2, gameDAO.find(gameid2).getBlackUsername());
    }

    @Test
    @Order(24)
    @DisplayName("Unsuccessful Find All Games Test")
    public void unsuccessfulFindAllGamesTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();
        int gameid1 = gameDAO.Insert("newgame1");
        int gameid2 = gameDAO.Insert("newgame2");

        String username1 = "white player";
        gameDAO.claimSpot(username1, gameDAO.find(gameid1), "WHITE");
        String username2 = "black player";
        gameDAO.claimSpot(username2, gameDAO.find(gameid2), "BLACK");

        List<Game> gameList = gameDAO.findAll();

        Assertions.assertNotEquals(0, gameList.size());
    }

    @Test
    @Order(25)
    @DisplayName("Clear Games Test")
    public void clearGamesTest() throws Exception {
        GameDAO gameDAO = GameDAO.getInstance();

        int gameid1 = gameDAO.Insert("newgame1");
        int gameid2 = gameDAO.Insert("newgame2");
        int gameid3 = gameDAO.Insert("newgame3");

        String username1 = "white player";
        gameDAO.claimSpot(username1, gameDAO.find(gameid1), "WHITE");
        String username2 = "black player";
        gameDAO.claimSpot(username2, gameDAO.find(gameid2), "BLACK");
        String username3 = "Spectator";
        gameDAO.claimSpot(username3, gameDAO.find(gameid3), null);

        gameDAO.clear();

        Assertions.assertEquals(0, gameDAO.findAll().size());
    }
}

