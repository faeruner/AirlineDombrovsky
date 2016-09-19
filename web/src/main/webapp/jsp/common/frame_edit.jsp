<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String entity_name = request.getParameter("entity_name");
    String command_select = request.getParameter("command_select");
    String command_insert = request.getParameter("command_insert");
    String command_update = request.getParameter("command_update");
    String page_fields = request.getParameter("page_fields");
%>
<html>
<head><title>AirlineDombrovsky: <%= entity_name %></title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="../../css/airline.css" rel="stylesheet" type="text/css"/>
    <%--<script type="text/javascript" src="../../js/bootstrap.min.js"></script>--%>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.2/jquery.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/locale/ru.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/js/bootstrap-datetimepicker.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/css/bootstrap-datetimepicker.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form action="controller" method="post">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-md-8">
                                <h3>
                                    <c:choose>
                                        <c:when test="${empty entity.id}">Insert </c:when>
                                        <c:otherwise>Edit </c:otherwise>
                                    </c:choose>
                                    <%= entity_name %>
                                </h3>
                            </div>
                            <div class="col-md-4">
                                <p style="text-align: right;">
                                    Debug info: session = ${sessionScope}
                                    <a href="controller?command=logout">Logout</a>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <c:choose>
                            <c:when test="${empty entity.id}">
                                <input type="hidden" name="command" value="<%= command_insert %>"/>
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="command" value="<%= command_update %>"/>
                                <input type="hidden" name="id" value="${entity.id}"/>
                            </c:otherwise>
                        </c:choose>
                        <div class="form-group row">
                            <label for="inputId" class="col-md-1 form-control-label"
                                   style="text-align: right;">Id</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="inputId"
                                       value="${entity.id}" disabled="disabled">
                            </div>
                        </div>
                        <jsp:include page="<%= page_fields %>" flush="true"/>
                    </div>
                    <div class="panel-footer panel-footer-custom">
                        <div class="row">
                            <div class="col-md-10 btn-toolbar" role="toolbar">
                                <input type="hidden" name="page_num" value="${requestScope.current_page}"/>
                                <button type="submit" class="btn btn-primary">
                                    <c:choose>
                                        <c:when test="${empty entity.id}">Insert</c:when>
                                        <c:otherwise>Save</c:otherwise>
                                    </c:choose>
                                </button>
                                <a class="btn btn-default" href="controller?command=<%= command_select %>">Close</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>