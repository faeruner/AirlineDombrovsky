<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Flight</title>

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
							<c:when test="${empty staff.id}">
								<h3>Insert Staff</h3>
							</c:when>
							<c:otherwise>
								<h3>Edit Staff</h3>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="panel-body">
						<form id="eventForm" method="post" class="form-horizontal" action="controller">
							<div class="form-group row">
								<label class="col-xs-1 control-label">Id</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" name="viewId" value="${staff.id}" disabled="disabled" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-xs-1 control-label">Name</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" name="name" value="${staff.name}">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-xs-1 control-label">Surname</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" name="surname" value="${staff.surname}">
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-xs-1 control-label">MemberType</label>
								<div class="col-xs-5">
									<select class="c-select form-control" name="member_type_id">
										<c:forEach var="item" items="${requestScope.member_type}">
											<c:choose>
												<c:when test="${staff.member.id eq item.id}">
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
								<div class="col-xs-2 col-xs-offset-1">
									<c:choose>
										<c:when test="${empty staff.id}">
											<input type="hidden" name="command" value="ins_staff" />
											<button type="submit" class="btn btn-primary btn-block">Insert</button>
										</c:when>
										<c:otherwise>
											<input type="hidden" name="id" value="${staff.id}" />
											<input type="hidden" name="command" value="upd_staff" />
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
			</div>
		</div>
	</div>
</body>
</html>