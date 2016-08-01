package ua.lutsenko.banking.command.account;



import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.entity.User;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class ManagementCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);
        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        wrapper.addNewAttributes("userId", userId);

        boolean isAddressExists = accountService.isAddressExist();
        wrapper.addAttrToSession("isAddressExists", isAddressExists);
        return "/jsp/accountPages/management.jsp";
    }
}
