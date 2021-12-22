<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Idea Info</title>
</head>
<body>

	<div >
		<div >
			<h1 >
				<c:out value="${idea.content}" />
			</h1>
			<p>Created By: ${idea.getCreator().getName()}</p>
			
		</div>
		
		<a href="/ideas/${idea.id}/edit" >Edit</a>
	</div>
</body>
</html>