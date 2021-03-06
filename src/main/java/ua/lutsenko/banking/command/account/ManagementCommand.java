package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.AddressDao;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.entity.User;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class ManagementCommand implements Command {
    /**
     * This method check user address and redirects to management page.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        Integer userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        wrapper.addNewAttribute("userId", userId);

        AddressDao addressDao = DaoFactory.getInstance().getAddressDao();
        boolean isAddressExists = addressDao.isAddressExist(userId);
        wrapper.addAttrToSession("isAddressExists", isAddressExists);
        return "/jsp/accountPages/management.jsp";
    }
}
