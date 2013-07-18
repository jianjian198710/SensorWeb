<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<form action="Controller" method="POST">
<div>
<label>Please input the SensorID:</label><input type="text" id="sensorID" name="sensorID"/>
</div>
<div>
<label>Please input the Description:</label><input type="text" id="description" name="description"/>
</div>
<div>
<label>Please input the Keyword:</label><input type="text" id="keyword" name="keyword"/>
</div>
<div>
<label>Please input the BeginTime:</label><input type="text" id="beginTime" name="beginTime"/>
</div>
<div>
<label>Please input the EndTime:</label><input type="text" id="endTime" name="endTime"/>
</div>
<div>
<label>Please input the SamplingTime:</label><input type="text" id="samplingTime" name="samplingTime"/>
</div>
<div>
<label>Please input the Easting</label><input type="text" id="easting" name="easting"/>
</div>
<div>
<label>Please input the Northing:</label><input type="text" id="northing" name="northing"/>
</div>
<div>
<label>Please input the Altitude:</label><input type="text" id="altitude" name="altitude"/>
</div>
<div>
<label>Please input the ObservableProperty:</label><input type="text" id="observableProperty" name="observableProperty"/>
</div>
<div>
<label>Please input the UOM:</label><input type="text" id="uom" name="uom"/>
</div>
<input type="submit" name="regist" value="submit" />
</form>
</body>
</html>