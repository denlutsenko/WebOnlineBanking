package ua.lutsenko.banking.command.account;


import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.ApplicationDao;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.entity.Application;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis Lutsenko.
 */
public class ApplicationsAccount implements Command {
    /**
     * This method builds list of new applications to open new Account.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        ApplicationDao applicationDao  = DaoFactory.getInstance().getApplicationDao();
        List<Application> applicationList = applicationDao.getApplications();

        wrapper.addNewAttributes("applicationList", applicationList);

        return "/jsp/adminPages/applicationsList.jsp";
    }
}
