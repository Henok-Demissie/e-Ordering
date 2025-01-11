package com.Adminlogin.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Adminlogin.DAO.LoginDao;
import com.Adminlogin.bean.LoginBean;

/**
 * Servlet implementation for Admin Login functionality.
 */
@WebServlet("/Adminlogin")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    /**
     * Initializes the LoginDao instance for this servlet.
     */
    @Override
    public void init() {
        loginDao = new LoginDao();
    }

    /**
     * Handles POST requests for admin login.
     *
     * @param request  HttpServletRequest object containing client request details.
     * @param response HttpServletResponse object to send responses to the client.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve login credentials from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create and populate a LoginBean instance
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            // Validate credentials using LoginDao
            if (loginDao.validate(loginBean)) {
                // Redirect to admin dashboard on successful login
                response.sendRedirect("Admin_index.jsp");
            } else {
                // Set error message and redirect to error page on login failure
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Invalid username or password.");
                response.sendRedirect("Error.jsp");
            }
        } catch (ClassNotFoundException e) {
            // Log and handle any exceptions
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }
}
