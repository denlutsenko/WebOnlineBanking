package ua.lutsenko.banking.command;

import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko.
 */
public interface Command {
     ResourceBundle pathBundle = ResourceBundle.getBundle("urlPath");
     String execute(RequestWrapper wrapper) throws SQLException;
}