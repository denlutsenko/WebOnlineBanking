package ua.lutsenko.banking.dao;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko on 7/25/2016.
 */
public class AddressDao {
    private DataSource ds;
    private static final Logger logger = Logger.getLogger(AddressDao.class);
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");

    AddressDao(DataSource ds) {
        this.ds = ds;
    }

    public boolean isAddressExist(int userId) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(resourceBundle.getString("IS_ADDRESS_EXIST"));
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.error("SQL error, " + e);
            return false;
        }
    }

    public boolean insertAddress(int userId, String country, String city, String street, String houseNumber) throws
            SQLException {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(resourceBundle.getString("INSERT_ADDRESS"));
            ps.setInt(1, userId);
            ps.setString(2, country);
            ps.setString(3, city);
            ps.setString(4, street);
            ps.setString(5, houseNumber);
            ps.execute();
            return true;
        } catch (SQLException e) {
            logger.error("SQL error, " + e);
            return false;
        }
    }
}