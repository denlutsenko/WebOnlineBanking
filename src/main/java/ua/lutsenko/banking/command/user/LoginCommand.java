package ua.lutsenko.banking.command.user;

import ua.lutsenko.banking.businesslogic.UserService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.entity.User;

/**
 * Created by Denis Lutsenko.
 */
public class LoginCommand implements Command {

    @Override
    public String execute(RequestWrapper wrapper) {
        UserService userService = new UserService(wrapper);

        if (userService.exist()) {
            wrapper.addAttrToSession("user", userService.getUserData());
            boolean isAdmin = ((User) wrapper.getSession().getAttribute("user")).isAdmin();
            if (isAdmin) {
                return "/jsp/adminPages/adminPersonalCabinet.jsp";
            } else {
                return "/jsp/userPages/personalCabinet.jsp";
            }
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}
