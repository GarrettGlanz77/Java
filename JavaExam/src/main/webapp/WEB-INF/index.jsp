<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Belt exam</title>
</head>
<body>
	<div class="container">
		<div class="notification">
			<h1>Welcome to JAVA </h1>
		</div>

		
		<section>
			<div >

				
				<div >
					<div >
						<h1 >Register!</h1>
					</div>
					<form:form method="POST" action="/registration"
						modelAttribute="user">
						<table >

							<tr>
								<td ><form:label path="name">Name:</form:label></td>
								<td ><form:input path="name"/></td>
							</tr>
							<tr>
								<td ><form:label path="email">Email:</form:label></td>
								<td ><form:input path="email"/></td>
							</tr>

							<tr>
								<td ><form:label path="password">Password:</form:label></td>
								<td ><form:input path="password"/></td>
							</tr>
							<tr>
								<td ><form:label path="passwordConfirmation">Password Confirmation:</form:label></td>
								<td ><form:input path="passwordConfirmation" /></td>
							</tr>
						</table>
						<div >
							<input type="submit" value="Register!"/>
						</div>
					</form:form>
					
					<p >
						<form:errors path="user.*" />
					</p>
				</div>
				<div >
					<div >
						<h1>Login!</h1>
					</div>

					<form method="post" action="/login">
						<table >
							<tr>
								<td><label for="email">Email:</label></td>
								<td><input type="text" id="email" name="email" /></td>
							</tr>
							<tr>
								<td ><label for="password">Password:</label></td>
								<td ><input type="password" id="password" name="password" /></td>
							</tr>
						</table>
						<div >
							<input type="submit" value="Login" />
						</div>
					</form>
					<p >
						<c:out value="${error}" />
					</p>
				</div>
			</div>
		</section>
	</div>
</body>
</html>