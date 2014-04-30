<%--
  Created by IntelliJ IDEA.
  User: satin
  Date: 29.08.13
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>AllDepartments</title>
</head>
<body>


<div  style="margin: auto;width: 600px " >
    <h1><c:out value="${department.departmentName}"></c:out></h1>
    <form  action="createEmployee" method="post">
        <button type="submit" name="depId" value="${department.departmentId}">Create New Employee</button>
    </form>

    <table style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="600">



        <tr>
            <td>First Name</td>
            <td>Last Name</td>
            <td>BirthDay</td>
            <td>Email</td>
            <td>Salary</td>
            <td>Department Options</td>
        </tr>

        <c:forEach var="employee" items="${employees}">

            <tr>
                <td><c:out value="${employee.firstName}"/></td>
                <td><c:out value="${employee.lastName}"/></td>

                <td>
                    <fmt:formatDate value="${employee.dayOfBirth}" var="parseDate" pattern="yyyy-MM-dd"/>
                    <c:out value="${parseDate}"/>
                </td>
                <td><c:out value="${employee.email}"/></td>
                <td><c:out value="${employee.salary}"/></td>
                <td style="width: 223px">
                    <form action="deleteEmployee" method="post">
                        <input type="hidden" name="depId" value="<c:out value="${department.departmentId}"/>">
                        <button type="submit" name="empId" value="${employee.employeeId}">Delete</button>
                    </form>
                    <form action="editEmployee" method="post">
                        <input type="hidden" name="empId" value="<c:out value="${employee.employeeId}"/>"/>
                        <button type="submit" name="depId" value="<c:out value="${department.departmentId}"/>">Edit</button>

                    </form>

                </td>
            </tr>
        </c:forEach>

    </table>
    <form:form action="/" method="get">
        <button type="submit">Main Page</button>
    </form:form>


</div>
</body>
</html>