package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

/**
 * Created by Denis Lutsenko.
 */
public class ConfirmationApplicationCommand implements Command {
    /**
     * This method receives all necessary data and approve opening new account for client.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     */
    @Override
    public String execute(RequestWrapper wrapper) {
        AccountService accountService = new AccountService(wrapper);
        if (accountService.insertApplication()) {
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}