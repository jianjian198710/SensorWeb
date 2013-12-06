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
			<td><div></div></td>
			<td><label>SensorID</label></td>
			<td><label>Status</label></td>
			<td><label>Easting</label></td>
			<td><label>Northing</label></td>
			<td><label>ObservableProperty</label></td>
			<td><label>Data</label></td>
		</tr>	
		<c:forEach items="${sessionScope.AllSensors}" var="sensor">
			<tr>
			<td><div><input type="checkbox" name="sid" value="${sensor.sensorID}"/></div></td>
			<td><label>${sensor.sensorID}</label></td>
			
			<td>
			<c:forEach items="${sessionScope.StartedSensorIds}" var="sensorIds">
				<c:if test="${sensorIds == sensor.sensorID }">
					Start!!!
				</c:if>
			</c:forEach>
			</td>
					
			<td><input type="text" name="easting" value="${sensor.easting}" readonly="readonly"/></td>
			<td><input type="text" name="northing" value="${sensor.northing}" readonly="readonly"/></td>
			<td><label>${sensor.observableProperty}</label></td>
			<td><label></label></td>
			</tr>
		</c:forEach>
	</table>
		<div>
		<input type="submit" name="start" value="Start"/>
		<input type="submit" name="stop" value="Stop"/>
		<input type="submit" name="startAll" value="StartAll"/>
		<input type="submit" name="stopAll" value="StopAll"/>
		</div>
	</form>
	<input type="button" id="showMap" name="showMap" value="showMap" onclick="initialize2();" />
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
	  var myLatlng = new google.maps.LatLng(-25.363882,131.044922);
	  var mapOptions = {
	    zoom: 4,
	    center: myLatlng
	  };
	  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

	  var marker = new google.maps.Marker({
	      position: myLatlng,
	      map: map,
	      title: 'Hello World!'
	  });
}

function initialize2() {
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
	  
	  
/* 	  var myLatLng2 = new google.maps.LatLng(-24.363892, 131.044932);
 	  var marker2 = new google.maps.Marker({
	        position: myLatLng2,
	        map: map,
	        title: 'Hello World!'
	    });  */
	  
  	  if(eastings.length==northings.length){
 		  console.log(eastings.length);
		  for(var i=0;i<eastings.length;i++){
			  console.log(eastings[i].value+","+northings[i].value);
		      var myLatLng = new google.maps.LatLng(Number(northings[0].value),Number(eastings[0].value));
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
	google.maps.event.addDomListener(window, 'load', initialize2);
</script>


</html>