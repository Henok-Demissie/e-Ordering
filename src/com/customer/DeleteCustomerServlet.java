package com.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("cusid");
        boolean isDeleted;

        try {
            // Attempt to delete the customer
            isDeleted = CustomerDBUtil.deleteCustomer(id);

            if (isDeleted) {
                // If deletion is successful, redirect to the customer insert page
                forwardRequest(request, response, "customerinsert.jsp");
            } else {
                // If deletion fails, retrieve customer details and forward to user account page
                List<Customer> cusDetails = CustomerDBUtil.getCustomerDetails(id);
                request.setAttribute("cusDetails", cusDetails);
                forwardRequest(request, response, "useraccount.jsp");
            }
        } catch (Exception e) {
            // Handle any errors that occur during the deletion or database operation
            e.printStackTrace();
            // Optionally, set an error message and forward to an error page
            request.setAttribute("errorMessage", "An error occurred while deleting the customer.");
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
