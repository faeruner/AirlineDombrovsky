<jsp:useBean id="entity" scope="request" type="by.pvt.module3.entity.Staff"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="form-group row">
    <label for="entity-name" class="col-xs-1 control-label" style="text-align: right;">Name</label>
    <div class="col-xs-5">
        <input id="entity-name" type="text" class="form-control" name="name" value="${entity.name}">
    </div>
</div>
<div class="form-group row">
    <label for="entity-surname" class="col-xs-1 control-label" style="text-align: right;">Surname</label>
    <div class="col-xs-5">
        <input id="entity-surname" type="text" class="form-control" name="surname" value="${entity.surname}">
    </div>
</div>

<div class="form-group row">
    <label for="entity-member-type" class="col-xs-1 control-label" style="text-align: right;">MemberType</label>
    <div class="col-xs-5">
        <select id="entity-member-type" class="c-select form-control" name="member_type_id">
            <c:forEach var="item" items="${requestScope.member_type}">
                <c:choose>
                    <c:when test="${entity.member.id eq item.id}">
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