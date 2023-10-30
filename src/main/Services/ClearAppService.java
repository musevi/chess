package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.UserDAO;
import DataAccess.GameDAO;
import Results.ClearAppResult;

/**
 * Service for ClearApp, which clears the database, removes all users, games, and authTokens
 */
public class ClearAppService {

    /**
     * returns ClearAppResult, no request body
     *
     * @return      Returns ClearAppResult
     */
    public ClearAppResult clearApp() {
        AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        GameDAO gameDAO = GameDAO.getInstance();

        authTokenDAO.clearAll();
        userDAO.clearAll();
        gameDAO.clearAll();

        return new ClearAppResult();
    }
}
