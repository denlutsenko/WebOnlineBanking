package ua.lutsenko.banking.command;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public interface Command {
     String MSG = "SUCCESS";
     String execute(RequestWrapper wrapper) throws SQLException;
}