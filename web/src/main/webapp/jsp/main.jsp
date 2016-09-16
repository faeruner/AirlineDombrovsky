<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Welcome admin</title>
    <%--<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>--%>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.2/jquery.js"></script>
    <%--<script type="text/javascript" src="../js/bootstrap.min.js"></script>--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <c:choose>
                        <c:when test="${empty user}">
                            <h3>Main Page</h3>
                        </c:when>
                        <c:otherwise>
                            <h3>Welcome, ${user.name}</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="panel-body">
                    <form class="form-inline" action="controller" method="post">
                        <button class="btn btn-primary" type="submit" name="command" value="sel_flight">
                            Flights
                        </button>
                        <button class="btn btn-primary" type="submit" name="command" value="sel_airport">
                            Airports
                        </button>
                        <button class="btn btn-primary" type="submit" name="command" value="sel_airline">
                            Airlines
                        </button>
                        <button class="btn btn-primary" type="submit" name="command" value="sel_user">
                            Users
                        </button>
                    </form>
                </div>
                <div class="panel-footer">
                    Debug info - session = ${sessionScope}
                    <a href="controller?command=logout">Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>