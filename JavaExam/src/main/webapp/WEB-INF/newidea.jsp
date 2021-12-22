<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> New Idea</title>
</head>
<body>
	<div >
		<div >
			<h1 >Create a new idea</h1>
		</div>
		
		<section>
			<div >
				<div >

					<form:form method="POST" action="/ideas/createnew" modelAttribute="newidea">

						<table>
							<tr>
								<td><form:label path="content">Name:</form:label></td>
								<td><form:input path="content" /></td>
								<td><form:errors path="content" /></td>
							</tr>
						</table>
						<div >
							<input type="submit" value="Create" /> 
						</div>
					</form:form>


				</div>
				<div ></div>
			</div>

		</section>
	</div>
</body>
</html>