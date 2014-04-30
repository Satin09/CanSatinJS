
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>CreatingEmployee</title>
</head>
<body>
<div  style="margin: auto;width: 600px " >
    <form:form modelAttribute="EMPLOYEE" action="addEmployee" method="get">
    <table style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="600">
        <tr>

            <h4>Create or Edit Employee</h4>

        </tr>
        <tr>
            <td>
                Enter Employee FirstName
            </td>

            <td>

                <form:input  path="firstName" value="${param['firstName'] eq null ? employee.firstName : param['firstName']}"/>
                <c:out value="${errors.get('firstName')}"/>
            </td>
        </tr>
        <tr>

            <td>
                Enter Employee LastName
            </td>

            <td>
                <form:input path="lastName" value="${param['lastName'] eq null ? employee.lastName : param['lastName']}"/>
                <c:out value="${errors.get('lastName')}"/>
            </td>

        </tr>
        <tr>

            <td>
                Enter Employee BirthDay
            </td>

            <td>
                <fmt:formatDate value="${employee.dayOfBirth}" var="parseDate" pattern="yyyy-MM-dd"/>
                <form:input path="dayOfBirth" value="${param['dayOfBirth']  eq null ? parseDate : param['dayOfBirth']}"/>
                <c:out value="${errors.get('dayOfBirth')}"/>
            </td>

        </tr>
        <tr>

            <td>
                Enter Employee Email
            </td>

            <td>
                <form:input path="email" value="${param['email'] eq null ? employee.email : param['email']}"/>
                <c:out value="${errors.get('email')}"/>
            </td>

        </tr>
        <tr>

            <td>
                Enter Employee Salary
            </td>

            <td>

                <form:input path="salary" value="${param['salary'] eq null ? employee.salary : param['salary']}"/>
                <c:out value="${errors.get('salary')}"/>

            </td>

        </tr>
        <c:choose>
            <c:when test="${employee.employeeId != null}">
                <tr>

                    <td>
                        Edit Employee Department
                    </td>

                    <td>
                        <select name="depID">
                            <c:forEach items="${departments}" var="department">
                                <option ${department.departmentId eq employee.department.departmentId ? 'selected':''} value="${department.departmentId}"><c:out value="${department.departmentName}"/></option>
                            </c:forEach>

                        </select>

                    </td>

                </tr>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="depID" value="">
            </c:otherwise>
        </c:choose>


    </table>
        <input type="hidden" name="depId" value="${department.departmentId}"/>
        <form:hidden path="employeeId" value="${employee.employeeId}"/>
    <button type="submit">Add or Edit Employee</button>
    </form:form>
</div>




</body>
</html>