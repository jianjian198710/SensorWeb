<%@page import="sensorweb.server.TCPServer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map-canvas { height: 50% ; postion:absolute; top:100px; width:100%}
    </style>
<title>ShowALLSensor</title>
</head>
<body>
	<form action="Controller">
	<table>
		<tr>
			<td><label>SensorID</label></td>
			<td><label>Status</label></td>
			<td><label>Easting</label></td>
			<td><label>Northing</label></td>
			<td><label>ObservableProperty</label></td>
		</tr>	
		<c:forEach items="${sessionScope.AllSensors}" var="sensor">
			<tr>
			<td><div><input type="checkbox" name="sid" value="${sensor.sensorID}"/></div></td>
			<td><input type="text" value="${sensor.sensorID}" readonly="readonly"/></td>
			
			<td>
			<c:forEach items="${sessionScope.SensorStatus}" var="sensorStatus">
				<c:if test="${sensorStatus.key == sensor.sensorID and (sensorStatus.value eq true) }">
					<input type="text" name="status" value="start" readonly="readonly"/>
				</c:if>
				<c:if test="${sensorStatus.key == sensor.sensorID and (sensorStatus.value eq false) }">
					<input type="text" name="status" value="stop" readonly="readonly"/>
				</c:if>
			</c:forEach>
			</td>
					
			<td><input type="text" name="easting" value="${sensor.easting}" readonly="readonly"/></td>
			<td><input type="text" name="northing" value="${sensor.northing}" readonly="readonly"/></td>
			<td><input type="text" value="${sensor.observableProperty}" readonly="readonly"/></td>
			<td><label></label></td>
			</tr>
		</c:forEach>
	</table>
		<div>
		<input type="submit" name="start" value="Start"/>
		<input type="submit" name="stop" value="Stop"/>
		<input type="submit" name="startAll" value="StartAll"/>
		<input type="submit" name="stopAll" value="StopAll"/>
		<input type="submit" name="detailedData" value="Detail"/>
		</div>
	</form>
	<input type="button" id="showMap" name="showMap" value="showMap" onclick="showMap();" />
	<br/>
	<div id="map-canvas" style="display:block">
	</div>
</body>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script type="text/javascript">
function showMap(){
	if(document.getElementById("map-canvas").style.display=="block"){
		document.getElementById("map-canvas").style.display="none";
	}else if(document.getElementById("map-canvas").style.display=="none"){
		document.getElementById("map-canvas").style.display="block";
	}
} 

function initialize() {
	  console.log("Start to get postion");
	  var eastings = document.getElementsByName("easting");
	  var northings = document.getElementsByName("northing");
	  console.log(eastings);
	  console.log(northings);
	  
	  var easting = Number(eastings[0].value);
	  var northing = Number(northings[0].value);
	  console.log("easting:"+easting);
	  console.log("northing:"+northing);
	  
	  var myLatlng = new google.maps.LatLng(northing,easting);  

	  var mapOptions = {
	    zoom: 4,
	    center: myLatlng
	  };
	  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	  
  	  if(eastings.length==northings.length){
 		  console.log(eastings.length);
		  for(var i=0;i<eastings.length;i++){
			  console.log(eastings[i].value+","+northings[i].value);
		      var myLatLng = new google.maps.LatLng(Number(northings[i].value),Number(eastings[i].value));
			  var marker = new google.maps.Marker({
			        position: myLatLng,
			        map: map,
			        title: 'Hello World!'
			    });
		  }
	  }  
	  console.log("Finish marker");
}


	var canvas = document.getElementById("showMap");
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</html>