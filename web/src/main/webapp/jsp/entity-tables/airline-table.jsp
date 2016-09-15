<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String command_select = request.getParameter("command_select");
    String command_delete = request.getParameter("command_delete");
%>
<table class="table">
    <thead class="thead-inverse">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${requestScope.entities}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>
                <jsp:include page="/jsp/common/frame_list-action.jsp" flush="true">
                    <jsp:param name="entity_id" value="${item.id}"/>
                    <jsp:param name="command_select" value="<%= command_select %>"/>
                    <jsp:param name="command_delete" value="<%= command_delete %>"/>
                </jsp:include>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
