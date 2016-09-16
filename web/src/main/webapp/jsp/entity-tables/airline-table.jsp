<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
      integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<%
    String command_select = request.getParameter("command_select");
    String command_delete = request.getParameter("command_delete");
%>
<table class="table table-hover table-sm table-inverse">
    <thead class="thead-default thead-inverse">
    <tr bgcolor="#f5f5f5">
        <th style="width: 100px">Actions</th>
        <th class="col-xs-1">ID</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${requestScope.entities}">
        <tr>
            <td>
                <jsp:include page="/jsp/common/frame_list-action.jsp" flush="true">
                    <jsp:param name="entity_id" value="${item.id}"/>
                    <jsp:param name="command_select" value="<%= command_select %>"/>
                    <jsp:param name="command_delete" value="<%= command_delete %>"/>
                </jsp:include>
            </td>
            <td>${item.id}</td>
            <td>${item.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
