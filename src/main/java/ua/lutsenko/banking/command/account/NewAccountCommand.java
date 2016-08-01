package ua.lutsenko.banking.command.account;



import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class NewAccountCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        return "/jsp/adminPages/applyNewAccount.jsp";
    }
}