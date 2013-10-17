<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<form action="Registration.jsp">
	<div>
	<input type="submit" name="newsensor" value="Register a new sensor"/>
	</div>
</form>
<form action="Controller">
	<div>
	<input type="submit" name="oldsensor" value="Already have a sensor"/>
	<input type="hidden" name="showAll" value="Show All the Registration Sensor"/>
	</div>
</form>
<form action="Unregistration.jsp">
	<div>
	<input type="submit" name="unregister" value="Unregister a sensor"/>
	</div>
</form>

</body>
</html>