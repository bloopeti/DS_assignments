package business.commands;

import dataAccess.dao.UserDAO;
import dataAccess.model.User;

public class LoginCommand implements Command {
    private String inputtedUserUsername;
    private String inputtedUserPassword;
    private User databaseUser;

    public LoginCommand(String inputtedUserUsername, String inputtedUserPassword) {
        this.inputtedUserUsername = inputtedUserUsername;
        this.inputtedUserPassword = inputtedUserPassword;
        this.databaseUser = new User();
    }

    public Object execute() {
//        UserDAO dao = new UserDAO();
        databaseUser = UserDAO.readUserByUsername(inputtedUserUsername);
        if (databaseUser != null)
            if (inputtedUserPassword.equals(databaseUser.getPass()))
                return databaseUser.getIsAdmin();

        return -1;
    }
}
