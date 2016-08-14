package ua.lutsenko.banking.controller;

import org.apache.log4j.Logger;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.CommandFactory;
import ua.lutsenko.banking.command.RequestWrapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */

/**
 * Class responsible for receiving all valid http requests.
 * It retrieves corresponding Command object for given request and execute the Command.
 * Then it does redirect for all POST requests and forwards or redirects for GET requests.
 *
 * @see #doGet method
 * @see #doPost method
 */
public class MainServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(MainServlet.class);

    /**
     * This method process all http post requests. Also this method use session to transfer path to GET request.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestWrapper wrapper = new RequestWrapper(request);
        Command command = CommandFactory.getInstance().getCommand(request);
        wrapper.extractParamValues();
        try {
            String path = command.execute(wrapper);
            HttpSession session = request.getSession(true);
            session.setAttribute("path", path);
            session.setAttribute("wrapper", wrapper);
        } catch (SQLException e) {
            LOG.error("DBError", e);
        }
        response.sendRedirect("/OnlineBanking24/" + request.getParameter("ok"));
    }

    /**
     * This method process all http get requests.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession(true);
        ((RequestWrapper) session.getAttribute("wrapper")).insertAttributes();
        request.getRequestDispatcher((String) session.getAttribute("path")).forward(request, response);
    }
}