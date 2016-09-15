<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String entity_name = request.getParameter("entity_name");
    String command_select = request.getParameter("command_select");
    String command_delete = request.getParameter("command_delete");
    String page_fields = request.getParameter("page_fields");
%>
<html>
<head><title>AirlineDombrovsky: <%= entity_name %></title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading"><h3><%= entity_name %> List</h3></div>
                <div class="panel-body">
                    <form class="form-inline" action="controller" method="post">
                        <div class="form-group">
                            <input type="hidden" name="id" value="0">
                            <input type="hidden" name="command" value="<%= entity_name %>">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Insert <%= entity_name %>
                            </button>
                        </div>
                        <div class="form-group">
                            <a class="btn btn-primary" href="controller?command=main">Main Page</a>
                        </div>
                    </form>
                </div>
                <jsp:include page="<%= page_fields %>" flush="true" >
                    <jsp:param name="command_select" value="<%= command_select %>"/>
                    <jsp:param name="command_delete" value="<%= command_delete %>"/>
                </jsp:include>
                <div class="panel-footer">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="<%= command_select %>">
                        <div class="btn-group" role="group" aria-label="...">
                            <c:forEach var="numPage" items="${requestScope.numPages}">
                                <c:choose>
                                    <c:when test="${numPage eq requestScope.current_page}">
                                        <button type="submit" name="page_num" value="${numPage}"
                                                class="btn btn-primary btn-xs">${numPage}</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" name="page_num" value="${numPage}"
                                                class="btn btn-secondary btn-xs">${numPage}</button>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </form>
                    <div>Page: ${requestScope.current_page}/${requestScope.numPages.size()}</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>