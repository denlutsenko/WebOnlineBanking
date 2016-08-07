package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;


/**
 * Created by Denis Lutsenko.
 */
public class NewAccountCommand implements Command {
    /**
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     */
    @Override
    public String execute(RequestWrapper wrapper) {

        String applicationId = wrapper.findParameterByName("applicationId");
        String userId = wrapper.findParameterByName("userId");
        String type = wrapper.findParameterByName("type");
        String balance = wrapper.findParameterByName("balance");
        String currency = wrapper.findParameterByName("currency");

        wrapper.addAttrToSession("applicationId",applicationId);
        wrapper.addAttrToSession("userId",userId);
        wrapper.addAttrToSession("type",type);
        wrapper.addAttrToSession("balance",balance);
        wrapper.addAttrToSession("currency",currency);

        return "/jsp/adminPages/applyNewAccount.jsp";
    }
}