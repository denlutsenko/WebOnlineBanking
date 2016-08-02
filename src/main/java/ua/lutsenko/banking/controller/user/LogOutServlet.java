package ua.lutsenko.banking.controller.user;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This servlet destroying current session and redirects to main page.
 */
public class LogOutServlet extends HttpServlet {
    private String path;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.getSession(true).invalidate();
        path = "/jsp/index.jsp";
        response.sendRedirect("/welcomePage");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
