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
 * Created by Denis Lutsenko on 7/25/2016.
 */
public class UserDao {
    private DataSource ds;
    private static final Logger logger = Logger.getLogger(UserDao.class);
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");

    UserDao(DataSource ds) {
        this.ds = ds;
    }

    public boolean exist(String email, String password) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(resourceBundle.getString("EXISTS"));
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.error("SQL error, ", e);
            return false;
        }
    }

    public boolean insert(String firstName, String lastName, String middleName, String phone, String email, String
            password) {
        boolean exist = exist(email, password);
        try (Connection conn = ds.getConnection()) {
            if (!exist) {
                PreparedStatement ps = conn.prepareStatement(resourceBundle.getString("INSERT_USER"));
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
            logger.error("Can't insert new user, ", e);
            return false;
        }
    }


    public User getUserData(String email, String password) {
        User user = null;
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(resourceBundle.getString("GET_USER_DATA"));
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
            logger.error("Can't get user data ", e);
        }
        return user;
    }
}