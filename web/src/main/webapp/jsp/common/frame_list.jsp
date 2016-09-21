<%@ page import="by.pvt.module3.filter.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String entity_name = request.getParameter("entity_name");
    String command_select = request.getParameter("command_select");
    String command_delete = request.getParameter("command_delete");
    String table_head = request.getParameter("table_head");
    String page_fields = request.getParameter("page_fields");
    String userAdmin = UserType.ADMINISTRATOR.name();
    String userType = ((UserType) session.getAttribute("userType")).name();
%>
<html>
<head><title>AirlineDombrovsky: <%= entity_name %>
</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link href="../../css/airline.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="../../js/jquery-2.1.2.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#errorModal").modal({
                show: ${requestScope.show_error}
            });
        });
    </script>
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
                                Debug info: session = ${sessionScope}
                                <a href="controller?command=logout">Logout</a>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <form class="form-inline col-md-1" action="controller" method="post">
                            <input type="hidden" name="id" value="0"/>
                            <input type="hidden" name="page_num" value="${requestScope.insertPageNum}"/>
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
                <table class="table table-hover table-sm table-inverse">
                    <thead class="thead-default thead-inverse">
                    <tr bgcolor="#f5f5f5">
                        <th style="width: 100px">Actions</th>
                        <%= table_head %>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${requestScope.entities}">
                        <tr>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="id" value="${item.id}">
                                    <input type="hidden" name="page_num" value="${requestScope.current_page}"/>
                                    <button type="submit" name="command" value="<%= command_select %>"
                                            class="btn btn-default btn-xs">edit
                                    </button>
                                    <button type="submit" name="command" value="<%= command_delete %>"
                                            class="btn btn-default btn-xs">delete
                                    </button>
                                </form>
                            </td>
                            <c:set var="item" value="${item}" scope="request"/>
                            <jsp:include page="<%= page_fields %>" flush="true"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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
<div class="modal fade" id="errorModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header alert alert-danger">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Error Message</h4>
            </div>
            <div class="modal-body">
                <p>${requestScope.text_error}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
</body>
</html>