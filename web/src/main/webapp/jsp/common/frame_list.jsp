<%@ page import="by.pvt.module3.filter.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String entity_name = request.getParameter("entity_name");
    String command_select = request.getParameter("command_select");
    String command_delete = request.getParameter("command_delete");
    String page_fields = request.getParameter("page_fields");
    String userAdmin = UserType.ADMINISTRATOR.name();
    String userType = ((UserType) session.getAttribute("userType")).name();

%>
<html>
<head><title>AirlineDombrovsky: <%= entity_name %>
</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="../../css/airline.css" rel="stylesheet" type="text/css"/>
    <%--<script type="text/javascript" src="../../js/bootstrap.min.js"></script>--%>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.2/jquery.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-8">
                            <c:choose>
                                <c:when test="${empty user}">
                                    <h3><%= entity_name %> List</h3>
                                </c:when>
                                <c:otherwise>
                                    <h3><%= entity_name %> List. Welcome, ${user.role.name}</h3>
                                    <p>${user.name}, hello!</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-4">
                            <p style="text-align: right;">
                                Debug info - session = <%= userAdmin + userType %> ${sessionScope}
                                <a href="controller?command=logout">Logout</a>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <form class="form-inline col-md-1" action="controller" method="post">
                            <input type="hidden" name="id" value="0" `/>
                            <button type="submit" class="btn btn-primary" name="command"
                                    value="<%= command_select %>">Insert
                            </button>
                        </form>
                        <form class="form-inline col-md-10" action="controller" method="post">
                            <% if (userAdmin.equals(userType)) {%>
                            <button class="btn btn-default" type="submit" name="command" value="sel_flight">
                                Flights
                            </button>
                            <button class="btn btn-default" type="submit" name="command" value="sel_airport">
                                Airports
                            </button>
                            <button class="btn btn-default" type="submit" name="command" value="sel_airline">
                                Airlines
                            </button>
                            <button class="btn btn-default" type="submit" name="command" value="sel_user">
                                Users
                            </button>
                            <% } else { %>
                            <button class="btn btn-default" type="submit" name="command" value="sel_crew">
                                Crew
                            </button>
                            <button class="btn btn-default" type="submit" name="command" value="sel_staff">
                                Staff
                            </button>
                            <% } %>
                        </form>
                    </div>
                </div>
                <jsp:include page="<%= page_fields %>" flush="true">
                    <jsp:param name="command_select" value="<%= command_select %>"/>
                    <jsp:param name="command_delete" value="<%= command_delete %>"/>
                </jsp:include>
                <div class="panel-footer panel-footer-custom">
                    <div class="row">
                        <div class="col-md-1">
                            Page: ${requestScope.current_page}/${requestScope.numPages.size()}
                        </div>
                        <form class="form-inline col-md-11" action="controller" method="post">
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
                                                    class="btn btn-default btn-xs">${numPage}</button>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>