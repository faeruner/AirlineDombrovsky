<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Airline</title>
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
							<c:when test="${empty airline.id}">
								<h3>Insert Airline</h3>
							</c:when>
							<c:otherwise>
								<h3>Edit Airline</h3>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="panel-body">
						<form action="controller" method="post">
							<div class="form-group row">
								<label for="inputId" class="col-xs-1 form-control-label">Id</label>
								<div class="col-xs-11">
									<input type="text" class="form-control" id="inputId"
										value="${airline.id}" disabled="disabled">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputName"
									class="col-xs-1 form-control-label text-xs-right">Name</label>
								<div class="col-xs-11">
									<input type="text" class="form-control" id="inputName"
										name="name" value="${airline.name}">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-1"></div>
								<div class="col-xs-2">
									<c:choose>
										<c:when test="${empty airline.id}">
											<input type="hidden" name="command" value="ins_airline" />
											<button type="submit" class="btn btn-primary btn-block">Insert</button>
										</c:when>
										<c:otherwise>
											<input type="hidden" name="id" value="${airline.id}" />
											<input type="hidden" name="command" value="upd_airline" />
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