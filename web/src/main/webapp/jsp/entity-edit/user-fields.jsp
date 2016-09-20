<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="form-group row">
    <label for="inputName"
           class="col-xs-1 form-control-label text-xs-right">Name</label>
    <div class="col-xs-11">
        <input type="text" class="form-control" id="inputName"
               name="name" value="${entity.name}">
    </div>
</div>
<div class="form-group row">
    <label for="inputSurname"
           class="col-xs-1 form-control-label text-xs-right">Surname</label>
    <div class="col-xs-11">
        <input type="text" class="form-control" id="inputSurname"
               name="surname" value="${user.surname}">
    </div>
</div>
<div class="form-group row">
    <label for="inputRole"
           class="col-xs-1 form-control-label text-xs-right">Role</label>
    <div class="col-xs-11">
        <select id="inputRole" class="c-select form-control" name="user_role_id">
            <c:forEach var="item" items="${requestScope.user_roles}">
                <c:choose>
                    <c:when test="${entity.role.id eq item.id}">
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