<jsp:useBean id="entity" scope="request" type="by.pvt.module3.entity.Crew"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="form-group row">
    <label for="entity-create-date" class="col-xs-1 control-label" style="text-align: right;">CreateDate</label>
    <div class="col-xs-3 date">
        <div class="input-group input-append date" id="inputCreateDate">
            <fmt:formatDate value="${entity.createDate}" pattern="dd.MM.yyyy" var="createDate"/>
            <input id="entity-create-date" type='text' class="form-control" name="create_date" value="${createDate}"/>
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
    <label class="col-xs-1 control-label" style="text-align: right;">Ready</label>
    <div class="col-xs-5">
        <div class="radio-inline">
            <label><input type="radio" name="ready" id="readyNo" value="0" ${requestScope.readyNo} />No</label>
        </div>
        <div class="radio-inline">
            <label><input type="radio" name="ready" id="readyYes" value="1" ${requestScope.readyYes} />Yes</label>
        </div>
    </div>
</div>
<div class="form-group row">
    <label for="entity-user" class="col-xs-1 control-label" style="text-align: right;">User</label>
    <div class="col-xs-5">
        <input id="entity-user" type="text" class="form-control" name="user"
               value="${entity.user.name} ${entity.user.surname}"
               disabled="disabled"/>
    </div>
</div>