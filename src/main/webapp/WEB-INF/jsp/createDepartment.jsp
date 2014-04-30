
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <html>
<head>
    <title>EditDepartment</title>
</head>
<body>
<%--@elvariable id="errors" type="java.util.Map<com.aimprosoft.department.controller.department.DepartmentController>"--%>
<%--@elvariable id="model" type="java.util.Map<com.aimprosoft.department.controller.department.DepartmentController>"--%>
<div  style="margin: auto;width: 600px " >

    <c:out value="${errors.get('departmentName')}"/>
    <%--<c:out  value="${errors.get('departmentName')}"></c:out>--%>
    <form:form modelAttribute="department" action="addDepartment" method="get">
    <table style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="600">
        <%--<form:hidden path="departmentId" value="${department.departmentId}"/>--%>
        <tr>
            <td>Enter new Department Name</td>
            <td>
                <form:input  path="departmentName" value="${param['depName'] eq null ? department.departmentName : param['depName']}"/>
                <input type="submit" value="Update or Add Department"/>
            </td>
        </tr>

    </table>

    </form:form>

</div>
</body>
</html>