<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
</head>
<body>

	<h1>Hello ${username}</h1>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>1</th>
				<th>2</th>
				<th>3</th>
			</tr>
		</thead>
		<tbody>


			<c:forEach items="${listInfo}" var="list" varStatus="loop">
				 <tr>
					<td>${loop.index + 1 }</td>
					<td>${list.id }</td>
					<td>${list.nameMember}</td>
					<td>${list.nameProduct}</td>
					<td>${list.statusCart}</td>
					<td>
						<a href='<c:url value=""></c:url>'> Update </a>
					</td>
					<td>
						<c:url value="" var="delete"></c:url>
						<form:form action="${delete }" method="post">
							<button type="submit" onclick="return confirm('xóa k?');">Xóa</button>
						</form:form>
					</td>
				</tr> 
			</c:forEach>

		</tbody>
	</table>

</body>
</html>