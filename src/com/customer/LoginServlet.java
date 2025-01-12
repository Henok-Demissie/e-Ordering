package com.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set up response output
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve user credentials from the form
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isValidUser = CustomerDBUtil.validate(userName, password);

        if (isValidUser) {
            // If login is successful, retrieve the customer details and forward to user account page
            List<Customer> cusDetails = CustomerDBUtil.getCustomer(userName);
            request.setAttribute("cusDetails", cusDetails);

            // Forward the request to the user account page
            RequestDispatcher dispatcher = request.getRequestDispatcher("useraccount.jsp");
            dispatcher.forward(request, response);
        } else {
            // If login fails, display an error message and redirect to login page
            showErrorMessage(out, "Your username or password is incorrect", "profilelogin.jsp");
        }
    }

    /**
     * Helper method to display an error message and redirect to a specified page.
     */
    private void showErrorMessage(PrintWriter out, String message, String redirectPage) {
        out.println("<script type='text/javascript'>");
        out.println("alert('" + message + "');");
        out.println("location='" + redirectPage + "';");
        out.println("</script>");
    }
}
