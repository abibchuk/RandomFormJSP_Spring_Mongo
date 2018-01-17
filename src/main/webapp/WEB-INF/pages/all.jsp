<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title> Test Page</title>
</head>
<body>

<table>
    <c:forEach items="${list}" var="msg">
        <tr>
            <td>Received params: ${msg}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>