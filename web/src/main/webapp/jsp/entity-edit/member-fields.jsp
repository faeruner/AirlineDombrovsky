<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${not empty entity.id}">

    <div class="container">
        <div class="row">
            <div class="col-md-12">

                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">Member List</h3></div>
                    <div class="panel-body">
                        <form id="memberForm" method="post" class="form-horizontal" action="controller">
                            <div class="form-group row">
                                <label class="col-xs-1 control-label">Staff</label>
                                <div class="col-xs-5 dropdown">
                                    <select class="c-select form-control" name="staff_id">
                                        <c:forEach var="item" items="${requestScope.staff}">
                                            <option value="${item.id}">${item.member.name}: ${item.name} ${item.surname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-xs-1">
                                    <c:if test="${not empty requestScope.staff}">
                                        <input type="hidden" name="page_num" value="${requestScope.current_page}"/>
                                        <input type="hidden" name="id" value="${entity.id}">
                                        <input type="hidden" name="command" value="ins_member"/>
                                        <button type="submit" class="btn btn-primary">Insert Staff</button>
                                    </c:if>
                                </div>
                            </div>
                        </form>
                    </div>
                    <table class="table">
                        <thead class="thead-inverse">
                        <tr>
                            <th style="width: 60px">Actions</th>
                            <th style="width: 60px">id</th>
                            <th>Member Type</th>
                            <th>Name</th>
                            <th>Surname</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="staff" items="${requestScope.entity.members}">
                            <tr>
                                <td>
                                    <form class="form-inline" action="controller" method="post">
                                        <input type="hidden" name="page_num" value="${requestScope.current_page}"/>
                                        <input type="hidden" name="id" value="${entity.id}">
                                        <input type="hidden" name="staff_id" value="${staff.id}">
                                        <input type="hidden" name="command" value="del_member">
                                        <button type="submit" class="btn btn-default btn-xs">delete</button>
                                    </form>
                                </td>
                                <td>${staff.id}</td>
                                <td>${staff.member.name}</td>
                                <td>${staff.name}</td>
                                <td>${staff.surname}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</c:if>
