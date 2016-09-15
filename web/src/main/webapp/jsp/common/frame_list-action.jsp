<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String entity_id = request.getParameter("entity_id");
    String command_select = request.getParameter("command_select");
    String command_delete = request.getParameter("command_delete");
%>
<form action="controller" method="post">
    <input type="hidden" name="id" value="<%= entity_id %>">
    <div class="btn-group" role="group" aria-label="...">
        <button type="submit" name="command" value="<%= command_select %>"
                class="btn btn-primary btn-xs">edit
        </button>
        <button type="submit" name="command" value="<%= command_delete %>"
                class="btn btn-danger btn-xs">delete
        </button>
    </div>
</form>
