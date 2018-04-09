<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
<link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet" />
<link href="<c:url value='/static/css/app.css'/>" rel="stylesheet" />
</head>
<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">List of Users</span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>FirstName</th>
						<th>LastName</th>
						<th>Email</th>
						<th>SSO_ID</th>
						<th width="100" />
						<th width="100" />
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.first_name }</td>
							<td>${user.last_name }</td>
							<td>${user.email }</td>
							<td>${user.sso_id }</td>
							<td><a href="<c:url value='/edit-user-${user.sso_id}' />"
								class="btn btn-success custom-width">edit</a></td>
							<td><a href="<c:url value='/delete-user-${user.sso_id}' />"
								class="btn btn-danger custom-width">delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="well">
			<a href="<c:url value='/newuser' />">Add New User</a>
		</div>
	</div>
</body>
</html>