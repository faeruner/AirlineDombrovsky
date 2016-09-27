<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head><title>AirlineDombrovsky</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="../css/airline.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-2.1.2.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form name="loginForm" method="POST" action="/login">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-8">
                            <h3>Login page</h3>
                            Please, enter your login and password
                        </div>
                        <div class="col-md-4">
                            <p style="text-align: right;">
                                Debug info: session = ${sessionScope}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="form-group row">
                        <label for="inputLogin" class="col-md-2 form-control-label"
                               style="text-align: right;">Login:</label>
                            <div class="col-md-3">
                                <input id="inputLogin" type="text" class="form-control" name="login" value=""/>
                            </div>
                        </div>
                    <div class="form-group row">
                        <label for="inputPassword" class="col-md-2 form-control-label" style="text-align: right;">Password:</label>
                            <div class="col-md-3">
                                <input id="inputPassword" type="password" class="form-control" name="password"
                                       value=""/>
                            </div>
                        </div>
                    <c:if test="${not empty errorLoginPassMessage}">${errorLoginPassMessage}<br/></c:if>
                    <c:if test="${not empty wrongAction}">${wrongAction}<br/></c:if>
                    <c:if test="${not empty nullPage}">${nullPage}<br/></c:if>
                </div>
                <div class="panel-footer">
                    <button type="submit" class="btn btn-primary" name="command" value="login">Log in</button>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>