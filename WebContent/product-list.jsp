<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Management | ASTU</title>
    <link rel="icon" type="image/x-icon" href="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZzPo9uv7MUCwwLTBVLgf29PIwG_Or3n2uVw&usqp=CAU">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f9f9f9;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        nav {
            background: #007bff;
            height: 80px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 20px;
            color: white;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        nav .logo {
            font-size: 24px;
            font-weight: bold;
            text-transform: uppercase;
        }

        nav ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
        }

        nav ul li {
            margin: 0 10px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
            transition: background 0.3s;
        }

        nav ul li a:hover, nav ul li a.active {
            background: #0056b3;
        }

        .container {
            margin: 20px auto;
            width: 90%;
            max-width: 1200px;
        }

        .container h1 {
            font-size: 36px;
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        #addbtn {
            display: inline-block;
            margin-bottom: 15px;
            padding: 10px 20px;
            background: #28a745;
            color: white;
            font-size: 16px;
            font-weight: bold;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }

        #addbtn:hover {
            background: #218838;
        }

        #table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        #table th, #table td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        #table th {
            background: #007bff;
            color: white;
            font-weight: bold;
        }

        #table tr:nth-child(even) {
            background: #f2f2f2;
        }

        #table tr:hover {
            background: #e9ecef;
        }

        a.action-link {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        a.action-link:hover {
            text-decoration: underline;
        }

        footer {
            text-align: center;
            padding: 10px 0;
            background: #007bff;
            color: white;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <nav>
        <div class="logo">ASTU</div>
        <ul>
            <li><a href="Admin_index.jsp">Home</a></li>
            <li><a href="ManageUsers/user-list.jsp">User Management</a></li>
            <li><a href="order-list.jsp">Order Management</a></li>
            <li><a href="delivery-list.jsp">Delivery Management</a></li>
            <li><a class="active" href="product-list.jsp">Menu Management</a></li>
        </ul>
    </nav>

    <div class="container">
        <h1>Product Details</h1>
        <a href="<%=request.getContextPath()%>/new4" id="addbtn">Add New Product</a>

        <table id="table">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Image Link</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${listProduct}">
                    <tr>
                        <td><c:out value="${product.id}" /></td>
                        <td><c:out value="${product.name}" /></td>
                        <td><c:out value="${product.category}" /></td>
                        <td><c:out value="${product.price}" /></td>
                        <td><c:out value="${product.image}" /></td>
                        <td>
                            <a href="edit4?id=<c:out value='${product.id}' />" class="action-link">Edit</a>
                            &nbsp;|&nbsp;
                            <a href="delete4?id=<c:out value='${product.id}' />" class="action-link">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <footer>
        &copy; 2025 ASTU. All Rights Reserved.
    </footer>
</body>
</html>
