package ua.lutsenko.banking.command.user;



import ua.lutsenko.banking.businesslogic.UserService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 7/25/2016.
 */
public class RegistrationCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        UserService userService = new UserService(wrapper);
        if (userService.insertUser()) {
            return "/jsp/index.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}