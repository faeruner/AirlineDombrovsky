<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Welcome dispatcher</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<c:choose>
							<c:when test="${empty user}">
								<h3>Main Page</h3>
							</c:when>
							<c:otherwise>
								<h3>Welcome, ${user.role.name}</h3>
								<p>${user.name}, hello!</p>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="panel-body">
						<table>
							<thead>
								<tr>
									<td>
										<form name="staffForm" method="POST" action="controller">
											<input type="hidden" name="command" value="sel_crew" />
											<button class="btn btn-primary" type="submit">Crew</button>
										</form>
									</td>
									<td>
										<form name="staffForm" method="POST" action="controller">
											<input type="hidden" name="command" value="sel_staff" />
											<button class="btn btn-primary" type="submit">Staff</button>
										</form>
									</td>
								</tr>
							</thead>
						</table>
					</div>
					<div class="panel-footer">
						Debug info - session = ${sessionScope} 
						<a href="controller?command=logout">Logout</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>