package ua.lutsenko.banking.controller.user;


import org.apache.log4j.Logger;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.CommandFactory;
import ua.lutsenko.banking.command.RequestWrapper;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This servlet accepts address data form request and call user commands.
 */
public class AddressServlet extends HttpServlet {
    private RequestWrapper wrapper;
    private String path;
    private static final Logger LOG = Logger.getLogger(AddressServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Command command = CommandFactory.getInstance().getCommand(request);
        wrapper = new RequestWrapper(request);
        wrapper.addParameter("country", request.getParameter("country"));
        wrapper.addParameter("city", request.getParameter("city"));
        wrapper.addParameter("street", request.getParameter("street"));
        wrapper.addParameter("houseNumber", request.getParameter("houseNumber"));
        try {
            wrapper.extractParamValues();
            path = command.execute(wrapper);
        } catch (SQLException e) {
            LOG.error("DBError", e);
        }
        response.sendRedirect("/bank24/managements/openingNewAccount/addressForm");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        wrapper.insertAttributes();
        request.getRequestDispatcher(path).forward(request, response);
    }
}