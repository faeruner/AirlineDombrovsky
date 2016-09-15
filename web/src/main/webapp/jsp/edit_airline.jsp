<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Edit Airline</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <form action="controller" method="post">

                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3>
                            <c:choose>
                                <c:when test="${empty entity.id}">Insert Airline</c:when>
                                <c:otherwise>Edit Airline</c:otherwise>
                            </c:choose>
                        </h3>
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
                            <label for="inputId" class="col-md-1 form-control-label">Id</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="inputId"
                                       value="${entity.id}" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputName"
                                   class="col-md-1 form-control-label text-xs-right">Name</label>
                            <div class="col-md-11">
                                <input type="text" class="form-control" id="inputName"
                                       name="name" value="${entity.name}">
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-md-10 btn-toolbar" role="toolbar">
                                <div class="btn-group" role="group">
                                    <button type="submit" class="btn btn-primary btn-default">
                                        <c:choose>
                                            <c:when test="${empty entity.id}">Insert</c:when>
                                            <c:otherwise>Save</c:otherwise>
                                        </c:choose>
                                    </button>
                                </div>
                                <div class="btn-group" role="group">
                                    <a class="btn btn-primary" href="controller?command=sel_airline">Close</a>
                                </div>
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