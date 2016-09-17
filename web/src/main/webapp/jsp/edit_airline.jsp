<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Edit Airline</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="../css/airline.css" rel="stylesheet" type="text/css"/>
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
                                    Airline
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
                                <input type="hidden" name="command" value="ins_airline"/>
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="command" value="upd_airline"/>
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
                        <div class="form-group row">
                            <label for="inputName" class="col-md-1 form-control-label"
                                   style="text-align: right;">Name</label>
                            <div class="col-md-11">
                                <input type="text" class="form-control" id="inputName"
                                       name="name" value="${entity.name}">
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer panel-footer-custom">
                        <div class="row">
                            <div class="col-md-10 btn-toolbar" role="toolbar">
                                    <button type="submit" class="btn btn-primary">
                                        <c:choose>
                                            <c:when test="${empty entity.id}">Insert</c:when>
                                            <c:otherwise>Save</c:otherwise>
                                        </c:choose>
                                    </button>
                                    <a class="btn btn-default" href="controller?command=sel_airline">Close</a>
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