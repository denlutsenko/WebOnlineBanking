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

/**
 * This is class which creates new CommandFactory(INSTANCE) and creates new Command.
 */
public class CommandFactory {
    private static CommandFactory INSTANCE;
    private Map<String, Command> commandMap = new HashMap<>();

    private CommandFactory() {
    }

    public static synchronized CommandFactory getInstance() {
        if (INSTANCE == null) INSTANCE = new CommandFactory();
        return INSTANCE;
    }

    {
        commandMap.put("PersonalCabinet", new LoginCommand());
        commandMap.put("RegistrationSuccess", new RegistrationCommand());
        commandMap.put("PersonalCabinet/RefillBalance", new RefillBalanceCommand());
        commandMap.put("PersonalCabinet/Form", new AddressCommand());
        commandMap.put("PersonalCabinet/AccountCreated", new ConfirmationAccountCommand());
        commandMap.put("PersonalCabinet/Accounts", new AllAccountsCommand());
        commandMap.put("PersonalCabinet/Management", new ManagementCommand());
        commandMap.put("PersonalCabinet/NewAccountApplication", new NewApplicationCommand());
        commandMap.put("PersonalCabinet/ApplicationSent", new ConfirmationApplicationCommand());
        commandMap.put("PersonalCabinet/AccountRefilled", new ConfirmationRefillingCommand());
        commandMap.put("PersonalCabinet/LockAccount", new BlockAccountCommand());
        commandMap.put("PersonalCabinet/Payments", new OperationsListCommand());
        commandMap.put("PersonalCabinet/CardLocked", new ConfirmationBlockingAccountCommand());
        commandMap.put("PersonalCabinet/InnerTransferSuccess", new InnerTransferCommand());
        commandMap.put("PersonalCabinet/WithdrawalSuccess", new WithdrawalCommand());
        commandMap.put("PersonalCabinet/PaymentSuccess", new PaymentCommand());
        commandMap.put("PersonalCabinet/Operations", new OperationsHistoryCommand());
        commandMap.put("PersonalCabinet/CurrentHistory", new CurrentHistoryCommand());
        commandMap.put("PersonalCabinet/BlockedAccounts", new BlockedAccountsCommand());
        commandMap.put("PersonalCabinet/ApplicationsList", new ApplicationsAccount());
        commandMap.put("PersonalCabinet/AccountUnlocked", new ConfirmationUnlockingCommand());
        commandMap.put("PersonalCabinet/ApplicationDeclined", new DecliningApplicationCommand());
        commandMap.put("PersonalCabinet/CreateAccount", new NewAccountCommand());
        commandMap.put("sessionEnd", new LogOutCommand());
    }

    /**
     * This method get Command by parameter value.
     *
     * @param request contain HttpServletRequest.
     * @return return  choose commandMap by value.
     */
    public Command getCommand(HttpServletRequest request) {
        String value = request.getParameter("ok");
        return commandMap.get(value);
    }
}