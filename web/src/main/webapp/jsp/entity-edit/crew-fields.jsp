<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="form-group row">
    <label class="col-xs-1 control-label">CreateDate</label>
    <div class="col-xs-3 date">
        <div class="input-group input-append date" id="inputCreateDate">
            <fmt:formatDate value="${entity.createDate}" pattern="dd.MM.yyyy" var="createDate"/>
            <input type='text' class="form-control" name="create_date" value="${createDate}"/>
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
    <label class="col-xs-1 control-label">Ready</label>
    <div class="col-xs-5">
        <div class="radio-inline">
            <label><input type="radio" name="ready" id="readyNo" value="0" ${readyNo} />No</label>
        </div>
        <div class="radio-inline">
            <label><input type="radio" name="ready" id="readyYes" value="1" ${readyYes} />Yes</label>
        </div>
    </div>
</div>
<div class="form-group row">
    <label class="col-xs-1 control-label">User</label>
    <div class="col-xs-5">
        <input type="text" class="form-control" name="user" value="${entity.user.name} ${entity.user.surname}"
               disabled="disabled"/>
    </div>
</div>
