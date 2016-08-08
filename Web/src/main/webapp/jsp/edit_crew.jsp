<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Crew</title>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.2/jquery.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.2/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/locale/ru.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/js/bootstrap-datetimepicker.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/css/bootstrap-datetimepicker.min.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<c:choose>
							<c:when test="${empty crew.id}">
								<h3>Insert Crew</h3>
							</c:when>
							<c:otherwise>
								<h3>Edit Crew</h3>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="panel-body">
						<form id="eventForm" method="post" class="form-horizontal" action="controller">
							<div class="form-group row">
								<label class="col-xs-1 control-label">Id</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" name="viewId" value="${crew.id}" disabled="disabled" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-xs-1 control-label">CreateDate</label>
								<div class="col-xs-3 date">
									<div class="input-group input-append date" id="inputCreateDate">
										<fmt:formatDate value="${crew.createDate}" pattern="dd.MM.yyyy" var="createDate"/>
	                    				<input type='text' class="form-control" name="create_date" value="${createDate}" />
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
									<input type="text" class="form-control" name="user" value="${crew.user.name} ${crew.user.surname}" disabled="disabled" />
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-2 col-xs-offset-1">
									<c:choose>
										<c:when test="${empty crew.id}">
											<input type="hidden" name="command" value="ins_crew" />
											<button type="submit" class="btn btn-primary btn-block">Insert</button>
										</c:when>
										<c:otherwise>
											<input type="hidden" name="id" value="${crew.id}" />
											<input type="hidden" name="command" value="upd_crew" />
											<button type="submit" class="btn btn-primary btn-block">Save</button>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="col-xs-2">
									<a class="btn btn-primary btn-block" href="controller?command=user">Main Page</a>
								</div>
							</div>
						</form>
					</div>
				</div>
				<c:if test="${not empty crew.id}">
					<div class="panel panel-primary">
						<div class="panel-heading"><h3 class="panel-title">Member List</h3></div>
						<div class="panel-body">
							<form id="memberForm" method="post" class="form-horizontal" action="controller">
								<div class="form-group row">
									<label class="col-xs-1 control-label">Staff</label>
									<div class="col-xs-5 dropdown">
										<select class="c-select form-control" name="staff_id">
											<c:forEach var="item" items="${requestScope.staff}">
												<option value="${item.id}" >${item.member.name}: ${item.name} ${item.surname}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-xs-1">
										<c:if test="${not empty requestScope.staff}">
											<input type="hidden" name="crew_id" value="${crew.id}">
											<input type="hidden" name="command" value="ins_member" />
											<button type="submit" class="btn btn-primary">Insert Staff</button>
										</c:if>
									</div>
								</div>
							</form>
						</div>
						<table class="table">
							<thead class="thead-inverse">
								<tr>
									<th>id</th>
									<th>Member Type</th>
									<th>Name</th>
									<th>Surname</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="staff" items="${requestScope.crew.members}">
									<tr>
										<td>${staff.id}</td>
										<td>${staff.member.name}</td>
										<td>${staff.name}</td>
										<td>${staff.surname}</td>
										<td>
											<form class="form-inline" action="controller" method="post">
												<input type="hidden" name="crew_id" value="${crew.id}">
												<input type="hidden" name="staff_id" value="${staff.id}">
												<input type="hidden" name="command" value="del_member">
												<button type="submit" class="btn btn-secondary btn-xs">delete</button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>