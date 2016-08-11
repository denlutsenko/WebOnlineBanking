package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.ApplicationDao;
import ua.lutsenko.banking.dao.DaoFactory;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class DecliningApplicationCommand implements Command {
    /**
     * This method declining application query form user.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        int id = Integer.parseInt(wrapper.findParameterByName("applicationId"));
        ApplicationDao applicationDao = DaoFactory.getInstance().getApplicationDao();
        boolean isDeleted = applicationDao.deleteApplication(id);
        if (isDeleted) {
            wrapper.addNewAttribute("msg", MSG);
            return "/jsp/adminPages/adminPersonalCabinet.jsp";
        } else {
            return "/jsp/reportPages/errorPage.jsp";
        }
    }
}