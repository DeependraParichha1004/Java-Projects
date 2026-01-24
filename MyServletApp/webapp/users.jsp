<%@ page import="java.util.List" %>

<html>
<body>

<h2>User List (Debug)</h2>

<%
    List<String> users = (List<String>) request.getAttribute("users");

    if (users == null) {
        out.println("users attribute is NULL");
    } else if (users.isEmpty()) {
        out.println("users list is EMPTY");
    } else {
        for (String u : users) {
            out.println("<p>" + u + "</p>");
        }
    }
%>

</body>
</html>
