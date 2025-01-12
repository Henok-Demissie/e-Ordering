package com.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String id = request.getParameter("cusid");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");
        String role = request.getParameter("role");
        boolean isUpdated;

        try {
            // Attempt to update the customer details
            isUpdated = CustomerDBUtil.updatecustomer(id, name, email, phone, address, username, password, role);

            if (isUpdated) {
                // If update is successful, retrieve updated customer details and forward to user account page
                List<Customer> cusDetails = CustomerDBUtil.getCustomerDetails(id);
                request.setAttribute("cusDetails", cusDetails);
                forwardRequest(request, response, "useraccount.jsp");
            } else {
                // If update fails, retrieve customer details and forward to user account page with message
                List<Customer> cusDetails = CustomerDBUtil.getCustomerDetails(id);
                request.setAttribute("cusDetails", cusDetails);
                forwardRequest(request, response, "useraccount.jsp");
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the update process
            e.printStackTrace();
            // Optionally, set an error message and forward to an error page
            request.setAttribute("errorMessage", "An error occurred while updating the customer details.");
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
