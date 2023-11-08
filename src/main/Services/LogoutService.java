package Services;

import Models.AuthToken;
import Results.LoginResult;
import Results.LogoutResult;
import DataAccess.AuthTokenDAO;

/**
 * Service for Logout, which logs out the user represented by the authToken
 */
public class LogoutService {

    /**
     * returns LogoutResult, no request body
     *
     * @return      Returns LogoutResult
     */
    public LogoutResult logout(String authToken) {
        try {
            AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
            authTokenDAO.getAuthToken(authToken);

            authTokenDAO.deleteAuthToken(authToken);

            return new LogoutResult();
        } catch(Exception e) {
            System.out.println(e.toString());
            if(e.getMessage().equals("token not found")) {
                return new LogoutResult("Error: unauthorized");
            }
            return new LogoutResult("Error: internal server error");
        }
    }
}
