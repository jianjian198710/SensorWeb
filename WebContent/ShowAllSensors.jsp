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
      #map-canvas { height: 50%;width:750px;margin:5px}
    </style>
<title>ShowALLSensor</title>
</head>
<body>
	<div>
	<form action="Controller">
	<table>
		<tr>
			<td></td>
			<td><input type="text" value="SensorID" readonly="readonly"/></td>
			<td><input type="text" value="Status" readonly="readonly"/></td>
			<td><input type="text" value="Easting" readonly="readonly"/></td>
			<td><input type="text" value="Northing" readonly="readonly"/></td>
			<td><input type="text" value="ObservableProperty" readonly="readonly"/></td>
		</tr>	
		<c:forEach items="${sessionScope.AllSensors}" var="sensor">
			<tr>
			<td><div><input type="checkbox" name="sid" value="${sensor.sensorID}" onchange="changeColor();"/></div></td>
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
		<div style="margin:5px">
		<input type="submit" name="start" value="Start"/>
		<input type="submit" name="stop" value="Stop"/>
		<input type="submit" name="startAll" value="StartAll"/>
		<input type="submit" name="stopAll" value="StopAll"/>
		<input type="submit" name="detailedData" value="Detail"/>
		<input type="submit" name="backToHome" value="BackToHome"/>
		</div>
	</form>
<!-- 	<div>
	<input type="button" id="showMap" name="showMap" value="showMap" onclick="showMap();" />
	</div> -->
	</div>
	<br/>
	<div id="map-canvas" style="display:block"></div>
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

var sensorids = document.getElementsByName("sid");
var status = document.getElementsByName("status");

function initialize() {
	  console.log("Start to get postion");
	  var eastings = document.getElementsByName("easting");
	  var northings = document.getElementsByName("northing");
	  console.log(sensorids);
	  console.log(status);
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
			  console.log(sensorids[i].value+","+status[i].value+","+eastings[i].value+","+northings[i].value);
		      var myLatLng = new google.maps.LatLng(Number(northings[i].value),Number(eastings[i].value));
		      var title = sensorids[i].value.toString();
		      if(status[i].value=="start"){
		    	  var pinColor = "66FF00";
		    	  var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|"+pinColor,
		    	        new google.maps.Size(21, 34),
		    	        new google.maps.Point(0,0),
		    	        new google.maps.Point(10, 34));
		      }else{
		    	  var pinColor = "FF3300";
		    	  var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|"+pinColor,
		    	        new google.maps.Size(21, 34),
		    	        new google.maps.Point(0,0),
		    	        new google.maps.Point(10, 34));
		      }
		      var contentString = '<div>'+sensorids[i].value+'</div>';
			  var marker = new google.maps.Marker({
			        position: myLatLng,
			        map: map,
			        animation: google.maps.Animation.DROP,
			        icon: pinImage,
			        title: title
			  });
			  addInfoWindow(marker,contentString);
		  }
	  }
  	  
	  function addInfoWindow(marker, message){
	  	  var infoWindow = new google.maps.InfoWindow({
	  	      content: message
	  	  });
	  	  google.maps.event.addListener(marker, 'click', function () {
	  	      infoWindow.open(map, marker);
	  	  });
	  }
	  
	  console.log("Finish marker");
}
/* 	var canvas = document.getElementById("showMap"); */
google.maps.event.addDomListener(window, 'load', initialize);


function changeColor(){
	for(var i=0;i<sensorids.length;i++){
		if(sensorids[i].checked==true){
			sensorids[i].parentNode.parentNode.parentNode.style.backgroundColor="#80BFFF";
		}else{
			sensorids[i].parentNode.parentNode.parentNode.style.backgroundColor="white";
		}
	}
}


/* var xmlrequest;
function createXMLHttpRequest()
{
	if(window.XMLHttpRequest)
	{
		xmlrequest = new XMLHttpRequest();
	}
	else if(window.ActiveXObject)
	{
		try
		{
			xmlrequest = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch(e)
		{
			try
			{
				xmlrequest = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){}
		}
	}
}

function getData(){
	createXMLHttpRequest();
	var uri = "Controller?
	if(sensorids.length==status.lengths){
		for(int i=0;i<sensorids-1.lengths;i++){
			console.log("sensorid="+sensorids[i].value+"&"+"status"+status[i].value)
			var temp = "sensorid="+sensorids[i].value+"&"+"status"+status[i].value;
		}
	}

	xmlrequest.open("GET",uri,true);
 	xmlrequest.onreadystatechange = processResponse; 
	xmlrequest.send(null);
}  */
</script>
</html>