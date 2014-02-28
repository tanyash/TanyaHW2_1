<%@ page import="store.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="store.User" %>
<%@ page import="store.XMLBinder" %>
<%--
  Created by IntelliJ IDEA.
  User: tanya
  Date: 2/28/14
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>

<body>
<%User curUser = (User)session.getAttribute("User");
  List<Order> userOrders = (List<Order>) session.getAttribute("userOrders");
    XMLBinder xmlBinder = (XMLBinder) session.getAttribute("XMLBinder");
%>
<h1>Hello, <%=curUser%></h1>
 <h3>   <%=userOrders%></h3>
<h3><%=xmlBinder%></h3>
<%if ((userOrders != null) && (userOrders.size() > 0)){%>
<h2>Please, get your orders:</h2>
<table>
<%for (Order order: userOrders){%>
    <tr>
        <td><%=order.getCar().getBrand()%></td>
        <td><%=order.getCar().getModel()%></td>
        <td><%=order.getCar().getYearProduced()%></td>
    </tr>
<%}%>
</table>
<%}%>


<h2>You can submit the new order:</h2>
<form name="input2" action="/order" method="POST">

    <table>
        <tr>
            <td>Brand:</td><td><input type="text" name="brand"/></td>
            <td>Model:</td><td><input type="text" name="model"/></td>
            <td>Year produced:</td><td><input type="text" name="year"/></td>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>

</form>

<h2><a href="/index.jsp" onclick="">Log out</a></h2>
</body>
</html>
