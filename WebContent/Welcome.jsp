<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<title>Welcome</title>
</head>
<body>
<div class="welcome">
	<ul id="nav">
        <li><a href="http://localhost:8080/SensorWeb/Welcome.jsp">Home</a></li>
        <li><a href="http://52north.org/">Resources</a>
           	<div>
                <ul>
	           		<li><a href="http://52north.org/communities/sensorweb">SensorWeb</a></li>
	            	<li><a href="http://52north.org/communities/sensorweb/incubation/sensorBus/index.html">SensorBus</a></li>
	               	<li><a href="http://52north.org/communities/sensorweb/sos/index.html">SOS</a></li>
	            </ul>
	        </div>
        </li>
        <li><a href="http://www.script-tutorials.com/about/">About</a></li>
        <li class="pad"></li>
    </ul>
    <div>
    <!--TODO写简介  -->
    </div>
    <div id="buttomdiv">
		<form action="Registration.jsp">
			<input type="submit" name="newsensor" value="Register a new sensor"/>
		</form>
		<form action="Controller">
			<input type="submit" name="oldsensor" value="Already have a sensor"/>
			<input type="hidden" name="showAll" value="Show All the Registration Sensor"/>
		</form>
		<form action="Unregistration.jsp">
			<input type="submit" name="unregister" value="Unregister a sensor"/>
		</form>
	</div>
</div>
</body>
</html>