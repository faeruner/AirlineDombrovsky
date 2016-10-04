<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="form-group row">
    <label for="inputName" class="col-md-1 form-control-label"
           style="text-align: right;">Name</label>
    <div class="col-md-11">
        <input type="text" class="form-control" id="inputName"
               name="name" value="${entity.name}">
    </div>
</div>
