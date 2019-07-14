<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"  uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
<h3>Logged in, Hi there!</h3>

<!-- display username, role -->
<p>
	User: <security:authentication property="principal.username"/>
	<br><br>
	Roles: <security:authentication property="principal.authorities"/>
</p>


<security:authorize access="hasRole('MANAGER')">
<p>
	<a href="${pageContext.request.contextPath}/leaders">Go to Leaders(For Managers)</a>
</p>
</security:authorize>


<security:authorize access="hasRole('DEVELOPER')">
<p>
	<a href="${pageContext.request.contextPath}/systems">Go to Systems(For Developers)</a>
</p>
</security:authorize>

<hr>

<form:form action="${pageContext.request.contextPath}/logout" 
			method="POST">
	<input type="submit" value="Logout"/>
</form:form>
</body>
</html>