package ua.lutsenko.banking.command.user;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.dao.UserDao;

/**
 * Created by Denis Lutsenko.
 */

public class RegistrationCommand implements Command {
    /**
     * This method accepts parameters from request and add new client to Data base.
     * @param wrapper this is wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     */
    @Override
    public String execute(RequestWrapper wrapper) {

        String firstName = wrapper.findParameterByName("firstName");
        String lastName = wrapper.findParameterByName("lastName");
        String middleName = wrapper.findParameterByName("middleName");
        String phone = wrapper.findParameterByName("phone");
        String email = wrapper.findParameterByName("email");
        String password = wrapper.findParameterByName("password");

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        boolean isInsertedUser = userDao.insert(firstName, lastName, middleName, phone, email, password);

        if (isInsertedUser) {
            return "/jsp/index.jsp";
        } else {
            return "/jsp/reportPages/error/jsp";
        }
    }
}