package ua.lutsenko.banking.command.account;


import org.apache.log4j.Logger;
import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class ConfirmationApplicationCommand implements Command {
    private static final Logger logger = Logger.getLogger(ConfirmationApplicationCommand.class);

    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);

        try {
            accountService.insertApplication();

            return "/jsp/userPages/personalCabinet.jsp";
        } catch (SQLException e) {
            logger.error("DB error", e);
            return "/jsp/reportPages/error.jsp";
        }
    }
}