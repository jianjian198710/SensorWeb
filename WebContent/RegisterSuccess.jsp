<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Success Registration!!!
<form action="Controller" method="GET">
	<div>
	<input type="submit" name="startNow" value="StartNow"/>
	</div>
	<div>
	<input type="submit" name="createSensorML" value="CreateSensorML"/>
	</div>
	<div>
	<input type="submit" name="registeAnother" value="Register Another One"/>
	</div>
	<div>
	<input type="submit" name="showAll" value="Show All the Registration Sensor"/>
	<input type="hidden" name="sensorID" value="${param.sensorID}"/>
	<input type="hidden" name="description" value="${param.description}"/>
	<input type="hidden" name="keyword" value="${param.keyword}"/>
	<input type="hidden" name="beginTime" value="${param.beginTime}"/>
	<input type="hidden" name="endTime" value="${param.endTime}"/>
	<input type="hidden" name="samplingTime" value="${param.samplingTime}"/>
	<input type="hidden" name="easting" value="${param.easting}"/>
	<input type="hidden" name="northing" value="${param.northing}"/>
	<input type="hidden" name="altitude" value="${param.altitude}"/>
	<input type="hidden" name="observableProperty" value="${param.observableProperty}"/>
	<input type="hidden" name="uom" value="${param.uom}"/>
	</div>
</form>
</body>
</html>