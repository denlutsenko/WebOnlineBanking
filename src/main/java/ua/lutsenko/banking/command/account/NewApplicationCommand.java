package ua.lutsenko.banking.command.account;


import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class NewApplicationCommand implements Command {
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