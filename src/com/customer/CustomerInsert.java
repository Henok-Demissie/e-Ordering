package com.customer;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomerInsert")
public class CustomerInsert extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve parameters from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String username = request.getParameter("uid");
        String password = request.getParameter("psw");
        String role = request.getParameter("role");

        boolean isSuccess;

        try {
            // Insert customer into the database
            isSuccess = CustomerDBUtil.insertCustomer(name, email, phone, address, username, password, role);

            // Forward to appropriate JSP based on success or failure
            if (isSuccess) {
                forwardRequest(request, response, "login.jsp");
            } else {
                forwardRequest(request, response, "unsuccess.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Forward to an error page in case of an exception
            forwardRequest(request, response, "error.jsp");
        }
    }

    /**
     * Helper method to forward the request to a specified JSP page.
     */
    private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
