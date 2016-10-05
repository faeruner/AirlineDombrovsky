<jsp:useBean id="entity" scope="request" type="by.pvt.module3.entity.User"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="form-group row">
    <label for="inputName"
           class="col-xs-1 form-control-label" style="text-align: right;">Name</label>
    <div class="col-xs-5">
        <input type="text" class="form-control" id="inputName"
               name="name" value="${entity.name}">
    </div>
</div>
<div class="form-group row">
    <label for="inputSurname"
           class="col-xs-1 form-control-label" style="text-align: right;">Surname</label>
    <div class="col-xs-5">
        <input type="text" class="form-control" id="inputSurname"
               name="surname" value="${entity.surname}">
    </div>
</div>
<div class="form-group row">
    <label for="inputLogin"
           class="col-xs-1 form-control-label" style="text-align: right;">Login</label>
    <div class="col-xs-3">
        <input type="text" class="form-control" id="inputLogin"
               name="login" value="${entity.login}">
    </div>
</div>
<div class="form-group row">
    <label for="inputPassword"
           class="col-xs-1 form-control-label" style="text-align: right;">Password</label>
    <div class="col-xs-3">
        <input type="text" class="form-control" id="inputPassword"
               name="password" value="${entity.password}">
    </div>
</div>
<div class="form-group row">
    <label for="inputRole"
           class="col-xs-1 form-control-label" style="text-align: right;">Role</label>
    <div class="col-xs-2">
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