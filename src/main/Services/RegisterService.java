package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.UserDAO;
import Models.AuthToken;
import Models.User;
import Requests.RegisterRequest;
import Results.LoginResult;
import Results.RegisterResult;

/**
 * Service for Register, which registers a new user
 */
public class RegisterService {

    /**
     * returns RegisterResult based on request
     * @param request   RegisterRequest
     * @return          Returns RegisterResult
     */
    public RegisterResult register(RegisterRequest request) {

        try {
            if(request.getUsername() == null || request.getPassword() == null) {
                throw new Exception("bad request");
            }

            User newUser = new User(request.getUsername(), request.getPassword(), request.getEmail());
            UserDAO userDAO = UserDAO.getInstance();
            userDAO.checkUserExists(request.getUsername());
            userDAO.createUser(newUser);

            AuthToken token = new AuthToken(request.getUsername());
            AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
            authTokenDAO.addAuthToken(token);

            return new RegisterResult(token.getAuthToken(), request.getUsername());

        } catch(Exception e) {
            System.out.println(e.toString());
            if(e.getMessage().equals("user already exists")) {
                return new RegisterResult("Error: already taken");
            } else if (e.getMessage().equals("bad request")) {
                return new RegisterResult("Error: bad request");
            }
            return new RegisterResult("Error: internal server error");
        }
    }
}
