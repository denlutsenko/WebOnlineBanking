package ua.lutsenko.banking.command.user;



import ua.lutsenko.banking.businesslogic.UserService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */


public class RegistrationCommand implements Command {
    /**
     * This method call user services and save new client.
     * @param wrapper this is wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) {
        UserService userService = new UserService(wrapper);
        if (userService.insertUser()) {
            return "/jsp/index.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}