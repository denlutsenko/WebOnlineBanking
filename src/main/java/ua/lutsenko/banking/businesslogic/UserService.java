package ua.lutsenko.banking.businesslogic;



import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.entity.User;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 7/24/2016.
 */
public class UserService {
    private RequestWrapper wrapper;
    private DaoFactory daoFactory;

    public UserService(RequestWrapper wrapper) {
        this.wrapper = wrapper;
        daoFactory = DaoFactory.getInstance();
    }

    public boolean exist() {
        return daoFactory.getUserDao().exist(wrapper.findParameterByName("email"),
                                              wrapper.findParameterByName("password"));
    }

    public User getUserData() {
        return daoFactory.getUserDao().getUserData(wrapper.findParameterByName("email"),
                                                    wrapper.findParameterByName("password"));
    }

    public boolean insertUser() {
        String firstName = wrapper.findParameterByName("firstName");
        String lastName = wrapper.findParameterByName("lastName");
        String middleName = wrapper.findParameterByName("middleName");
        String phone = wrapper.findParameterByName("phone");
        String email = wrapper.findParameterByName("email");
        String password = wrapper.findParameterByName("password");
        return daoFactory.getUserDao().insert(firstName, lastName, middleName, phone, email, password);
    }

    public boolean insertAddress() throws SQLException {

        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();

        String country = wrapper.findParameterByName("country");
        String city = wrapper.findParameterByName("city");
        String street = wrapper.findParameterByName("street");
        String houseNumber = wrapper.findParameterByName("houseNumber");

        return daoFactory.getAddressDao().insertAddress(userId, country, city, street, houseNumber);
    }
}
