package ua.lutsenko.banking.controller.account;


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
 * Created by Denis Lutsenko on 7/26/2016.
 */

public class ManagementServlet extends HttpServlet {
    private RequestWrapper wrapper;
    private String path;
    private static final Logger logger = Logger.getLogger(ManagementServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        wrapper = new RequestWrapper(request);
        wrapper.addParameter("currCardId", request.getParameter("currCardId"));
        wrapper.addParameter("operationType", request.getParameter("operationType"));
        wrapper.addParameter("operationSumm", request.getParameter("operationSumm"));
        wrapper.addParameter("accountType", request.getParameter("accountType"));
        wrapper.addParameter("accountCurrency", request.getParameter("accountCurrency"));
        wrapper.addParameter("accountBalance", request.getParameter("accountBalance"));



        Command command = CommandFactory.getInstance().getCommand(request);
        try {
            wrapper.extractParamValues();
            path = command.execute(wrapper);
        } catch (SQLException e) {
            logger.error("DBError" + e);
        }
        response.sendRedirect("/bank24/managements");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        wrapper.insertAttributes();
        request.getRequestDispatcher(path).forward(request, response);

    }
}
