<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<tiles:insertAttribute name="params"/>
<html>
<head><title>AirlineDombrovsky: ${entity_name}
</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link href="${pageContext.request.contextPath}/css/airline.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/locale/ru.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#errorModal").modal({
                show: ${requestScope.show_error}
            });
        });
    </script>
</head>
<body>
<header>
    <tiles:insertAttribute name="header"/>
</header>
<section id="main_list">
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form action="${action}" method="post">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-md-8">
                                <h3>
                                    <c:choose>
                                        <c:when test="${empty entity.id}">Insert </c:when>
                                        <c:otherwise>Edit </c:otherwise>
                                    </c:choose>
                                    ${entity_name}
                                </h3>
                            </div>
                            <div class="col-md-4">
                                <p style="text-align: right;">
                                    Debug info: session = ${sessionScope}
                                    <a href="/logout">Logout</a>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="form-group row">
                            <label for="inputId" class="col-md-1 form-control-label"
                                   style="text-align: right;">Id</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="inputId"
                                       value="${entity.id}" disabled="disabled">
                            </div>
                        </div>
                        <tiles:insertAttribute name="page-fields"/>
                    </div>
                    <div class="panel-footer panel-footer-custom">
                        <div class="row">
                            <div class="col-md-10 btn-toolbar" role="toolbar">
                                <input type="hidden" name="page_num" value="${requestScope.current_page}"/>
                                <c:choose>
                                    <c:when test="${empty entity.id}">
                                        <button type="submit" class="btn btn-primary" name="command" value="add">
                                            Insert
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="id" value="${entity.id}"/>
                                        <button type="submit" class="btn btn-primary" name="command" value="upd">Save
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                                <button type="submit" class="btn btn-default" name="command" value="list">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</section>
<section>
    <tiles:insertAttribute name="advanced-edit"/>
</section>
<footer id="footer">
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>