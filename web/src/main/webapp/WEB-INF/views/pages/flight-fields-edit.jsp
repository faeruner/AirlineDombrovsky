<jsp:useBean id="entity" scope="request" type="by.pvt.module3.entity.Flight"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="form-group row">
    <label for="entity-code" class="col-xs-1 control-label" style="text-align: right;">Code</label>
    <div class="col-xs-5">
        <input id="entity-code" type="text" class="form-control" name="code" value="${entity.code}">
    </div>
</div>
<div class="form-group row">
    <label for="entity-dep-date" class="col-xs-1 control-label" style="text-align: right;">DepDate</label>
    <div class="col-xs-4 date">
        <div class="input-group input-append date" id="inputDepDate">
            <fmt:formatDate value="${entity.depDate}" pattern="dd.MM.yyyy" var="depDate"/>
            <input id="entity-dep-date" type='text' class="form-control" name="departure" value="${depDate}"/>
            <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#inputDepDate').datetimepicker({
                format: 'DD.MM.YYYY'
            });
        });
    </script>
</div>
<div class="form-group row">
    <label for="inputReturnDate" class="col-xs-1 control-label" style="text-align: right;">ReturnDate</label>
    <div class="col-xs-4 date">
        <div class="input-group input-append date" id="inputReturnDate">
            <fmt:formatDate value="${entity.returnDate}" pattern="dd.MM.yyyy" var="retDate"/>
            <input type='text' class="form-control" name="return_date"/>
            <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#inputReturnDate').datetimepicker({
                format: 'DD.MM.YYYY'
            });
        });
    </script>
</div>
<div class="form-group row">
    <label for="entity-cre-date" class="col-xs-1 control-label" style="text-align: right;">CreateDate</label>
    <div class="col-xs-5 date">
        <div class="input-group input-append date" id="inputCreateDate">
            <fmt:formatDate value="${entity.createDate}" pattern="dd.MM.yyyy" var="creDate"/>
            <input id="entity-cre-date" type='text' class="form-control" name="create_date" value="${creDate}"/>
            <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#inputCreateDate').datetimepicker({
                format: 'DD.MM.YYYY'
            });
        });
    </script>
</div>
<div class="form-group row">
    <label for="entity-arrival" class="col-xs-1 control-label" style="text-align: right;">Arrival</label>
    <div class="col-xs-5">
        <select id="entity-arrival" class="c-select form-control" name="airport_arv_id">
            <c:forEach var="item" items="${requestScope.arrival}">
                <c:choose>
                    <c:when test="${entity.arrival.id eq item.id}">
                        <option selected value="${item.id}">${item.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${item.id}">${item.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>
</div>
<div class="form-group row">
    <label for="entity-departure" class="col-xs-1 control-label" style="text-align: right;">Departure</label>
    <div class="col-xs-5">
        <select id="entity-departure" class="c-select form-control" name="airport_dep_id">
            <c:forEach var="item" items="${requestScope.departure}">
                <c:choose>
                    <c:when test="${entity.departure.id eq item.id}">
                        <option selected value="${item.id}">${item.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${item.id}">${item.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>
</div>
<div class="form-group row">
    <label for="entity-airline" class="col-xs-1 control-label" style="text-align: right;">Airline</label>
    <div class="col-xs-5">
        <select id="entity-airline" class="c-select form-control" name="airline_id">
            <c:forEach var="item" items="${requestScope.airline}">
                <c:choose>
                    <c:when test="${entity.airline.id eq item.id}">
                        <option selected value="${item.id}">${item.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${item.id}">${item.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>
</div>
<div class="form-group row">
    <label for="entity-crew" class="col-xs-1 control-label" style="text-align: right;">CrewId</label>
    <div class="col-xs-5">
        <select id="entity-crew" class="c-select form-control" name="crew_id">
            <c:forEach var="item" items="${requestScope.crew}">
                <c:choose>
                    <c:when test="${entity.crew.id eq item.id}">
                        <option selected value="${item.id}">${item.id}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${item.id}">${item.id}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>
</div>
<div class="form-group row">
    <label for="entity-user" class="col-xs-1 control-label" style="text-align: right;">User</label>
    <div class="col-xs-5">
        <input id="entity-user" type="text" class="form-control" name="user" value="${entity.user.login}"
               disabled="disabled"/>
    </div>
</div>