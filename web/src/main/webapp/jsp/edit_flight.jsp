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
							<c:when test="${empty flight.id}">
								<h3>Insert Flight</h3>
							</c:when>
							<c:otherwise>
								<h3>Edit Flight</h3>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="panel-body">
						<form id="eventForm" method="post" class="form-horizontal" action="controller">
							<div class="form-group row">
								<label class="col-xs-1 control-label">Id</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" name="viewId" value="${flight.id}" disabled="disabled" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-xs-1 control-label">Code</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" name="code" value="${flight.code}">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-xs-1 control-label">DepDate</label>
								<div class="col-xs-3 date">
									<div class="input-group input-append date" id="inputDepDate">
										<fmt:formatDate value="${flight.depDate}" pattern="dd.MM.yyyy" var="depDate"/>
	                    				<input type='text' class="form-control" name="departure" value="${depDate}" />
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
								<label class="col-xs-1 control-label">ReturnDate</label>
								<div class="col-xs-3 date">
									<div class="input-group input-append date" id="inputReturnDate">
										<fmt:formatDate value="${flight.returnDate}" pattern="dd.MM.yyyy" var="retDate"/>
	                    				<input type='text' class="form-control" name="return_date" value="${retDate}" />
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
								<label class="col-xs-1 control-label">CreateDate</label>
								<div class="col-xs-3 date">
									<div class="input-group input-append date" id="inputCreateDate">
										<fmt:formatDate value="${flight.createDate}" pattern="dd.MM.yyyy" var="creDate"/>
	                    				<input type='text' class="form-control" name="create_date" value="${creDate}" />
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
								<label class="col-xs-1 control-label">Arrival</label>
								<div class="col-xs-5">
									<select class="c-select form-control" name="airport_arv_id">
										<c:forEach var="item" items="${requestScope.arrival}">
											<c:choose>
												<c:when test="${flight.arrival.id eq item.id}">
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
								<label class="col-xs-1 control-label">Departure</label>
								<div class="col-xs-5">
									<select class="c-select form-control" name="airport_dep_id">
										<c:forEach var="item" items="${requestScope.departure}">
											<c:choose>
												<c:when test="${flight.departure.id eq item.id}">
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
								<label class="col-xs-1 control-label">Airline</label>
								<div class="col-xs-5">
									<select class="c-select form-control" name="airline_id">
										<c:forEach var="item" items="${requestScope.airline}">
											<c:choose>
												<c:when test="${flight.airline.id eq item.id}">
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
								<label class="col-xs-1 control-label">CrewId</label>
								<div class="col-xs-5">
									<select class="c-select form-control" name="crew_id">
										<c:forEach var="item" items="${requestScope.crew}">
											<c:choose>
												<c:when test="${flight.crew.id eq item.id}">
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
								<label class="col-xs-1 control-label">User</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" name="user" value="${flight.user.login}" disabled="disabled" />
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-2 col-xs-offset-1">
									<c:choose>
										<c:when test="${empty flight.id}">
											<input type="hidden" name="command" value="ins_flight" />
											<button type="submit" class="btn btn-primary btn-block">Insert</button>
										</c:when>
										<c:otherwise>
											<input type="hidden" name="id" value="${flight.id}" />
											<input type="hidden" name="command" value="upd_flight" />
											<button type="submit" class="btn btn-primary btn-block">Save</button>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="col-xs-2">
									<a class="btn btn-primary btn-block" href="controller?command=main">Main Page</a>
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