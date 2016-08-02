package ua.lutsenko.banking.command.user;


import ua.lutsenko.banking.businesslogic.UserService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class AddressCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        UserService userService = new UserService(wrapper);
        if (userService.insertAddress()) {
            return "/jsp/accountPages/applicationForm.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}