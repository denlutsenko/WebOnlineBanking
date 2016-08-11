package ua.lutsenko.banking.dao;

import org.apache.log4j.Logger;
import ua.lutsenko.banking.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This class works with User table and makes next operations:
 * - check is exists user.
 * - insert new user.
 * - get all user data.
 */
public class UserDao {
    private DataSource ds;
    private static final Logger LOG = Logger.getLogger(UserDao.class);
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sqlstatements");

    UserDao(DataSource ds) {
        this.ds = ds;
    }

    /**
     * This method checks is user exists in table 'user'.
     *
     * @param email    user email
     * @param password user password
     * @return boolean flag
     */
    public boolean exist(String email, String password) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("EXISTS"));
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return false;
        }
    }

    /**
     * This method inserts data of new user.
     *
     * @return boolean flag.
     * @parameters contains all necessary information to make operation.
     */
    public boolean insert(String firstName, String lastName, String middleName, String phone, String email, String
            password) {
        boolean exist = exist(email, password);
        try (Connection conn = ds.getConnection()) {
            if (!exist) {
                PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_USER"));
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, middleName);
                ps.setString(4, phone);
                ps.setString(5, email);
                ps.setString(6, password);
                ps.execute();
            }
            return true;
        } catch (SQLException e) {
            LOG.error("Can't insert new user, ", e);
            return false;
        }
    }

    /**
     * This method gets all user data from table.
     *
     * @return object of user with user data.
     * @parameters contains user e-mail and password to get all user data.
     */
    public User getUserData(String email, String password) {
        User user = null;
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("GET_USER_DATA"));
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String phoneNumber = rs.getString("phone");
                boolean isAdmin = rs.getBoolean("is_admin");
                user = new User(firstName, lastName, middleName);
                user.setId(id);
                user.setPhoneNumber(phoneNumber);
                user.setAdmin(isAdmin);
            }
        } catch (SQLException e) {
            LOG.error("Can't get user data ", e);
        }
        return user;
    }

    public boolean deleteUser(String email, String password) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("DELETE_USER"));
            ps.setString(1, email);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("Can't delete application", e);
            return false;
        }
    }
}
