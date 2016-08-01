package ua.lutsenko.banking.command;

import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko on 5/21/2016.
 */
public interface Command {
     String MSG = "SUCCESS";
     ResourceBundle pathBundle = ResourceBundle.getBundle("urlPath");
     String execute(RequestWrapper wrapper) throws SQLException;
}