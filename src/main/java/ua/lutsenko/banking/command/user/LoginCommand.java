package ua.lutsenko.banking.command.user;


import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.dao.UserDao;
import ua.lutsenko.banking.entity.User;

/**
 * Created by Denis Lutsenko.
 */
public class LoginCommand implements Command {
    /**
     * This method accepts user login, password and checks is current user exists in data base.
     * Also this method check is current user admin or only client.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     */
    @Override
    public String execute(RequestWrapper wrapper) {
        String login = wrapper.findParameterByName("email");
        String password = wrapper.findParameterByName("password");

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        boolean isExists = userDao.exist(login, password);

        if (isExists) {
            wrapper.addAttrToSession("user", userDao.getUserData(login, password));
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
