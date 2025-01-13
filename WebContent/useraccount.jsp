<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile | ASTU</title>
    <link rel="icon" type="image/x-icon" href="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZzPo9uv7MUCwwLTBVLgf29PIwG_Or3n2uVw&usqp=CAU" alt="Profile Icon">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 90%;
            margin: 0 auto;
        }

        h1 {
            text-align: center;
            text-transform: uppercase;
            color: #333;
            margin: 20px 0;
        }

        #table {
            width: 60%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        #table th, #table td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        #table th {
            background-color: #d9e6fa;
            color: blue;
            text-align: center;
        }

        #table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        #table tr:hover {
            background-color: #f1f1f1;
        }

        .button-container {
            text-align: center;
            margin: 20px 0;
        }

        .button-container a {
            text-decoration: none;
        }

        input[type="button"] {
            background-color: blue;
            color: white;
            padding: 10px 20px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="button"]:hover {
            background-color: darkblue;
        }

        input[type="button"]:focus {
            outline: 2px solid darkblue;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>My Account</h1>
        <c:choose>
            <c:when test="${not empty cusDetails}">
                <table id="table">
                    <c:forEach var="cus" items="${cusDetails}">
                        <tr><td>My ID</td><td>${cus.id}</td></tr>
                        <tr><td>Name</td><td>${cus.name}</td></tr>
                        <tr><td>Email</td><td>${cus.email}</td></tr>
                        <tr><td>Phone Number</td><td>${cus.phone}</td></tr>
                        <tr><td>Delivery Address</td><td>${cus.address}</td></tr>
                        <tr><td>My User Name</td><td>${cus.username}</td></tr>
                        <tr><td>Role</td><td>${cus.role}</td></tr>
                    </c:forEach>
                </table>

                <div class="button-container">
                    <c:url value="updatecustomer.jsp" var="cusupdate">
                        <c:param name="id" value="${cus.id}" />
                    </c:url>
                    <a href="${cusupdate}">
                        <input type="button" value="Update My Data">
                    </a>

                    <c:url value="deletecustomer.jsp" var="cusdelete">
                        <c:param name="id" value="${cus.id}" />
                    </c:url>
                    <a href="${cusdelete}">
                        <input type="button" value="Delete My Account">
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <p style="text-align: center; color: red;">No customer details found.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
