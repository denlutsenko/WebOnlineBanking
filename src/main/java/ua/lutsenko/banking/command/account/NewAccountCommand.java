package ua.lutsenko.banking.command.account;



import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class NewAccountCommand implements Command {
    /**
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     */
    @Override
    public String execute(RequestWrapper wrapper){
        return "/jsp/adminPages/applyNewAccount.jsp";
    }
}