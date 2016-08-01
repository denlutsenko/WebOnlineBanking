package ua.lutsenko.banking.command;

import ua.lutsenko.banking.command.account.*;
import ua.lutsenko.banking.command.user.AddressCommand;
import ua.lutsenko.banking.command.user.LoginCommand;
import ua.lutsenko.banking.command.user.RegistrationCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Denis Lutsenko.
 */

public class CommandFactory {
    private final static CommandFactory INSTANCE = new CommandFactory();
    private Map<String, Command> commandMap = new HashMap<>();

    {
        commandMap.put("login", new LoginCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("refillBalance", new RefillBalanceCommand());
        commandMap.put("newAddress", new AddressCommand());
        commandMap.put("confirmNewAccount", new ConfirmationAccountCommand());
        commandMap.put("userAccounts", new AllAccountsCommand());
        commandMap.put("userManagement", new ManagementCommand());
        commandMap.put("newApplication", new NewApplicationCommand());
        commandMap.put("saveNewApplication", new ConfirmationApplicationCommand());
        commandMap.put("confirmPayment", new ConfirmationRefillingCommand());
        commandMap.put("lockAccount", new BlockAccountCommand());
        commandMap.put("userPayments", new OperationsListCommand());
        commandMap.put("confirmLocking", new ConfirmationBlockingAccountCommand());
        commandMap.put("innerTransfer", new InnerTransferCommand());
        commandMap.put("withdrawal", new WithdrawalCommand());
        commandMap.put("payment", new PaymentCommand());
        commandMap.put("userOperations", new OperationsHistoryCommand());
        commandMap.put("currentHistory", new CurrentHistoryCommand());
        commandMap.put("blockedAccounts", new BlockedAccountsCommand());
        commandMap.put("applicationsList", new ApplicationsAccount());
        commandMap.put("unlockAccount", new ConfirmationUnlockingCommand());
        commandMap.put("declineApplication", new DecliningApplicationCommand());
        commandMap.put("createAccount", new NewAccountCommand());

    }

    public static synchronized CommandFactory getInstance() {
        return INSTANCE;
    }


    /**
     * This method get Command by parameter value
     *
     * @param request
     * @return return  choose commandMap by value
     */
    public Command getCommand(HttpServletRequest request) {
        String value = request.getParameter("ok");
        return commandMap.get(value);
    }
}