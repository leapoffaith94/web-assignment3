<%-- 
    Document   : userLogged
    Created on : Nov 1, 2018, 5:35:06 PM
    Author     : chaoguo
--%>


<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="assignment3.UserBean"
         %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" 
              content="text/html; charset=UTF-8">
        <title>   User Logged Successfully   </title>
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="style.css"/>
    </head>

    <body>

    <center>
        <% UserBean currentUser =  (UserBean) session.getAttribute("currentSessionUser");%>

        Welcome <%= currentUser.getFirstName() + " " + currentUser.getLastName()%>
    </center>

</body>

</html>
