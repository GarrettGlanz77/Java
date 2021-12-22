<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dash-board/welcome</title>
</head>
<body>

	<div >
		<div >
			<h1 >
				Welcome! <c:out value="${user.name}" />			
			</h1>
			
			<a href="/logout">Logout</a>
		</div>
		<p >Ideas: </p> 

		<table >
			<thead>
				<tr>
					<td>Idea</td>
					<td>Created By:</td>
				</tr>
			</thead>
		<tbody>
			<c:forEach items="${ideas}" var ="idea">
				<tr>
					<td><a href="/ideas/${idea.id}">${idea.content}</a></td>
					<td>${idea.getCreator().getName()}</td>
				</tr>
			</c:forEach>				
		</tbody>
		</table>
		
		
	</div>
	<p>
	<a href="/ideas/new" >Create Idea</a>
	</p>
</body>
</html>