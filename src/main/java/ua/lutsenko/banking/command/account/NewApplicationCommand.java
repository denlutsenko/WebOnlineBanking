package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class NewApplicationCommand implements Command {
/**
 * This method builds list of new applications to open new Account.
 *
 * @param wrapper wrapper for HttpServletRequest.
 * @return path to load a new jsp page.
 * @throws SQLException
 * @see ConfirmationAccountCommand
  */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        boolean addressExists = (boolean) wrapper.findSessionAttrByName("isAddressExists");
        if (addressExists) {
            return "/jsp/accountPages/applicationForm.jsp";
        } else {
            return "/jsp/userPages/addressForm.jsp";
        }
    }
}