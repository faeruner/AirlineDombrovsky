<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head><title>AirlineDombrovsky</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3>Login page</h3>
                    Please, enter your login and password
                </div>
                <div class="panel-body">
                    <form name="loginForm" method="POST" action="login">
                        <input type="hidden" name="command" value="login"/>
                        Login:<br/>
                        <div class="row">
                            <div class="col-md-3">
                                <input type="text" class="form-control" name="login" value=""/>
                            </div>
                        </div>
                        <br/>
                        Password:<br/>
                        <div class="row">
                            <div class="col-md-3">
                                <input type="password" class="form-control" name="password" value=""/>
                            </div>
                        </div>
                        ${errorLoginPassMessage}<br/>
                        ${wrongAction}<br/>
                        ${nullPage}<br/>
                        <button type="submit" class="btn btn-primary">Log in</button>
                    </form>
                </div>
                <div class="panel-footer">
                    Links for guest...<br/>
                    Debug info - session = ${sessionScope}
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>