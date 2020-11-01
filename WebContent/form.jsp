<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Záznamy</title>
<link href="themes.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="script.js"></script>
</head>
<body class="wrapper default">

<%
session.setMaxInactiveInterval(1);
%>
<c:if test="${not empty alert}">
	<script>alert("Form submitted successfully");</script>
</c:if>

<section class="controls">
	  <button class="controls" data-theme="default" onClick=myFunction(this)>Default</button>  
	  <button class="controls" data-theme="light" onClick=myFunction(this)>Light</button>	  
	  <button class="controls" data-theme="dark" onClick=myFunction(this)>Dark</button>
</section>

<h2 class="heading">Records:</h2>

<div class="show_data">
<table>
    <thead>
    <tr>
        <th>Jméno</th>
        <th>Přijmění</th>
        <th>Email</th>
        <th>Datum narození</th>
        <th>Pohlaví</th>
        <th>Zájmy</th>
        <th>Najvyšší dosáhnuté vzdělání</th>
    </tr>
    </thead>
    <c:forEach items="${records}" var="record">
        <tr>
            <td><c:out value="${record.name}"/></td>
            <td><c:out value="${record.surname}"/></td>
            <td><c:out value="${record.email}"/></td>
            <td><c:out value="${record.date}"/></td>
            <td><c:out value="${record.gender}"/></td>
            <td>
            	
            	<c:forEach items="${record.interests}" var="interest">
            		<c:out value="${interest}"/><br>
            	</c:forEach>            	
            </td>
            <td><c:out value="${record.education}"/></td>
        </tr>
    </c:forEach>
</table>
</div>

<h2 class="heading">Zadejte záznam:</h2>
<c:if test="${not empty error}">
    <div class="error-div" style="border: solid 1px red; background-color: yellow; padding: 10px">
        <c:out value="${error}"/>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/records/add" method="post">
    <table>
        <tr>
            <th>Jméno a Příjmení:</th>
            <td><input type="text" name="name" required pattern="([^\u0000-\u007F]|\w)+ ([^\u0000-\u007F]|\w)+"/></td>
        </tr>
        <tr>
        	<th>Email:</th>
            <td><input type="email" name="email" required/></td>
        </tr>
        <tr>
            <th>Datum narození:</th>
            <%!String currDate = LocalDate.now().toString(); %>
            <td><input type="date" name="birthday" required max=<%=currDate%>></td>
        </tr>
        <tr>
            <th>Pohlaví:</th>
            <td>
            <input type="radio" id="male" name="gender" value="male" required>
			<label for="male">Male</label>
			<input type="radio" id="female" name="gender" value="female" required>
			<label for="female">Female</label>
            </td>
        </tr>
        <tr>        
        	<th>Zájmy:</th>
        	<td>
        	<input type="checkbox" name="interest" value="reading">Reading
        	<input type="checkbox" name="interest" value="cycling">Cycling
        	<input type="checkbox" name="interest" value="drawing">Drawing
        </tr>
        <tr>        
        	<th></th>
        	<td>
        	<input type="checkbox" name="interest" value="singing">Singing
        	<input type="checkbox" name="interest" value="sleeping">Sleeping
        	<input type="checkbox" name="interest" value="playing games">Playing games
        </tr>
        <tr>
        	<th></th>
        	<td>
        	<input type="checkbox" name="interestOther" value="other">Other
        	<input type="text" name="interestOtherVal"/>(separate by a comma)
        </tr>
        <tr>
            <th>Nejvyšší dosáhnuté vzdělání:</th>
            <td>
            <select name="education">
            	<c:forEach items="${educationValues}" var="education">
            		<option value="<c:out value="${education}"/>"><c:out value="${education}"/></option>
            	</c:forEach>
            </select>
            </td>
        </tr>
    </table>
    <input type="Submit" value="Zadat" />
</form>

</body>
</html>
