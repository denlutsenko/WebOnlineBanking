package ua.lutsenko.banking.command.user;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.AddressDao;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.entity.User;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class AddressCommand implements Command {
    /**
     * This method accept parameters from request(wrapper) and save new address of current user.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        String country = wrapper.findParameterByName("country");
        String city = wrapper.findParameterByName("city");
        String street = wrapper.findParameterByName("street");
        String houseNumber = wrapper.findParameterByName("houseNumber");

        AddressDao addressDao = DaoFactory.getInstance().getAddressDao();
        boolean isAddressAdded = addressDao.insertAddress(userId, country, city, street, houseNumber);

        if (isAddressAdded) {
            return "/jsp/accountPages/applicationForm.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}