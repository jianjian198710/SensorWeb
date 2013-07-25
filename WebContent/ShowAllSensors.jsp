<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td><div><input type="checkbox"/></div></td>
			<td><label>SensorID</label></td>
			<td><label>Status</label></td>
			<td><label>Easting</label></td>
			<td><label>Northing</label></td>
			<td><label>ObservableProperty</label></td>
			<td><label>Data</label></td>
			<td><label>Operation</label></td>
		</tr>	
<%-- 		<c:forEach items="${list}" var="li">
			<div>
			</div>
		</c:forEach> --%>
	</table>
	<form action="controller">
	<div>
	<input type="submit" name="start" value="Start"/>
	</div>
	<div>
	<input type="submit" name="stop" value="Stop"/>
	</div>
	</form>
</body>
</html>