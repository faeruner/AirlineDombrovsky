<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flights</title>

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
					<div class="panel-heading"><h3>Flight List</h3></div>
					<div class="panel-body">
						<form id="btnhead" class="form-inline" action="controller" method="post">
							<div class="form-group">
								<input type="hidden" name="id" value="0"> 
								<input type="hidden" name="command" value="sel_flight">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">Insert Flight</button>
							</div>
							<div class="form-group">
								<a class="btn btn-primary" href="controller?command=main">Main Page</a>
							</div>
						</form>
					</div>
					<table class="table">
						<thead class="thead-inverse">
							<tr>
								<th>Actions</th>
								<th>id</th>
								<th>Code</th>
								<th>Departure</th>
								<th>Return</th>
								<th>Create</th>
								<th>Arrival</th>
								<th>Departure</th>
								<th>Airline</th>
								<th>CrewId</th>
								<th>User</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="flight" items="${requestScope.flight}">
								<tr>
									<td>
										<table>
											<thead>
												<tr>
													<td>
														<form class="form-inline" action="controller" method="post">
															<input type="hidden" name="id" value="${flight.id}">
															<input type="hidden" name="command" value="sel_flight">
															<button type="submit" class="btn btn-secondary btn-xs">edit</button>
														</form>
													</td>
													<td>
														<form class="form-inline" action="controller" method="post">
															<input type="hidden" name="id" value="${flight.id}">
															<input type="hidden" name="command" value="del_flight">
															<button type="submit" class="btn btn-secondary btn-xs">delete</button>
														</form>
													</td>
												</tr>
											</thead>
										</table>
									</td>
									<td>${flight.id}</td>
									<td>${flight.code}</td>
									<td><fmt:formatDate value="${flight.depDate}" pattern="dd.MM.yyyy" /></td>
									<td><fmt:formatDate value="${flight.returnDate}" pattern="dd.MM.yyyy" /></td>
									<td><fmt:formatDate value="${flight.createDate}" pattern="dd.MM.yyyy" /></td>
									<td>${flight.arrival.name}</td>
									<td>${flight.departure.name}</td>
									<td>${flight.airline.name}</td>
									<td>${flight.crew.id}</td>
									<td>${flight.user.name} ${flight.user.surname}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>