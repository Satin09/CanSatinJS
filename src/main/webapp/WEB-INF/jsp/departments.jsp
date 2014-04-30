<%--
  Created by IntelliJ IDEA.
  User: satin
  Date: 27.08.13
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>AllDepartments</title>
</head>
<body>

<%--&lt;%&ndash;@elvariable id="departments" type="java.util.List<com.aimprosoft.department.models.Department>"&ndash;%&gt;--%>
<%--<div  style="margin: auto;width: 600px " >--%>
<%--<h1>Departments List</h1>--%>

<%--<table style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="600">--%>

<%--<c:forEach var="department" items="${departments}">--%>

<%--<tr>--%>
<%--<td><c:out value="${department.departmentName}"/></td>--%>
<%--<td style="width: 223px">--%>
<%--<form action="deleteDepartment" method="post">--%>
<%--<button type="submit" name="depId" value="<c:out value="${department.departmentId}"/>">Delete</button>--%>
<%--</form>--%>
<%--<form action="showEmployee" method="post">--%>
<%--<button type="submit" name="depId" value="<c:out value="${department.departmentId}"/>">Show Employees</button>--%>
<%--</form>--%>
<%--<form action="editDepartment" method="post">--%>
<%--<button type="submit" name="depId" value="<c:out value="${department.departmentId}"/>">Edit Department</button>--%>
<%--</form>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>

<%--</table>--%>


<%--</div>--%>

<%--@elvariable id="departments" type="java.util.List<com.aimprosoft.department.models.Department>"--%>
<div style="margin: auto;width: 600px ">
    <h1>Departments List</h1>



    <form:form  modelAttribute="DEPARTMENT" action="createDepartment" method="post">
        <form:hidden path="departmentId"/>

        <button type="submit">AddDepartment</button>

    </form:form>

    <table  style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="600">


        <c:forEach var="department" items="${departments}">

            <tr>
                <td><c:out value="${department.departmentName}"/></td>
                <td style="width: 223px">
                    <form action="deleteDepartment" method="post">
                        <button type="submit" name="depId" value="<c:out value="${department.departmentId}"/>">Delete
                        </button>
                    </form>
                    <form:form  modelAttribute="DEPARTMENT" action="showEmployee" method="get">
                        <button type="submit" name="depId" value="<c:out value="${department.departmentId}"/>">Show
                            Employees
                        </button>
                    </form:form>
                    <form action="editDepartment" method="post">
                        <button type="submit" name="depId" value="<c:out value="${department.departmentId}"/>">Edit
                            Department
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>

</div>
</body>
</html>