<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="random" class="java.util.Random" scope="application" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить</title>
</head>
<body>
<form:form method="POST" action="/add">
    <table>
        <c:forEach var = "i" begin = "0" end = "${random.nextInt(4)}">
                <tr>
                    <td>Param${i}:</td>
                    <td><input type = "text" name = "param${i}" /></td>
                </tr>
        </c:forEach>
        <tr>
            <td colspan="2">
                <input type="submit" />
            </td>
        </tr>
    </table>

</form:form>
</body>
</html>
