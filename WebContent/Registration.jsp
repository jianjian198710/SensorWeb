<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <style type="text/css">
      input { width: 250px;}
    </style>
<title>Registration</title>
</head>
<body>
<form action="Controller" method="GET">
<table>
<tr>
	<td><input type="text" value="Please input the SensorID:" readonly="readonly"/></td>
	<td><input type="text" id="sensorID" name="sensorID"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the Description:" readonly="readonly"/></td>
	<td><input type="text" id="description" name="description"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the Keyword:" readonly="readonly"/></td>
	<td><input type="text" id="keyword" name="keyword"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the BeginTime:" readonly="readonly"/></td>
	<td><input type="text" id="beginTime" name="beginTime"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the EndTime:" readonly="readonly"/></td>
	<td><input type="text" id="endTime" name="endTime"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the SamplingTime:" readonly="readonly"/></td>
	<td><input type="text" id="samplingTime" name="samplingTime"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the Easting:" readonly="readonly"/></td>
	<td><input type="text" id="easting" name="easting"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the Northing:" readonly="readonly"/></td>
	<td><input type="text" id="northing" name="northing"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the Altitude:" readonly="readonly"/></td>
	<td><input type="text" id="altitude" name="altitude"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the ObservableProperty:" readonly="readonly"/></td>
	<td><input type="text" id="observableProperty" name="observableProperty" onkeyup="showHint(this.value)"/></td>
</tr>
<tr>
	<td><input type="text" value="Please input the UOM:" readonly="readonly"/></td>
	<td><input type="text" id="uom" name="uom"/></td>
</tr>
<tr>
	<td><input type="submit" name="register" value="Register"/></td>
</tr>
</table>
</form>
<script type="text/javascript" src="jquery-1.9.1.min.js"></script>
<script type="text/javascript">
 	function showHint(str) {
		var xmlhttp;
		if (str.length == 0) {
			document.getElementById("observableProperty").innerHTML = "";
			return;
		}
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("observableProperty").value = xmlhttp.responseText;
			}
		};
		xmlhttp.open("GET", "AjaxResponse/hint.jsp?q=" + str, true);
		xmlhttp.send();
	}  
</script>
</body>
</html>