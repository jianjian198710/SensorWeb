<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style type="text/css">
      #newNumber { background:none repeat scroll 0 0 #FEFDED;border:1px solid #F9F2A7;color:#F48C12;display:block;text-align:center;display:none;width:63.5%}
    </style>
<title>DetailedData</title>
</head>
<body>
	<div style="display:none">
	<form action="Controller">
		<input type="submit"  id="refresh" name="refresh" value="refresh"/>
	</form>
	</div>
	<table>
		<tr>
			<div id="newNumber" onclick="getLatestData();" onmouseover="addLine();" onmouseout="resetLine();"/>
		</tr>
		<tr>
			<td><input type="text" value="time" readonly="readonly"/></td>
			<td><input type="text" value="SensorID" readonly="readonly"/></td>
			<td><input type="text" value="ObservableProperty" readonly="readonly"/></td>
			<td><input type="text" value="Value" readonly="readonly"/></td>
			<td><input type="text" value="Uom" readonly="readonly"/></td>
		</tr>
		<c:forEach items="${sessionScope.currentPageDatas}" var="data" varStatus="status">
		<c:choose>
			<c:when test="${status.index%2==0}">
      			<tr bgcolor="#DFDFDF">
            </c:when>
            <c:otherwise>
       			 <tr bgcolor="#BFDFFF">
            </c:otherwise>
            </c:choose>
			<td><input type="text" value="${data.timeStamp}" readonly="readonly"/></td>
			<td><input type="text" name="sensorId" value="${data.sensorID}" readonly="readonly"/></td>
			<td><input type="text" value="${data.observableProperty}" readonly="readonly"/></td>
			<td><input type="text" value="${data.value}" readonly="readonly"/></td>
			<td><input type="text" value="${data.uom}" readonly="readonly"/></td>
			</tr>
		</c:forEach>
	</table>
	<div style="margin-left:5px">
	<form action="Controller">
		Page:<input type="text" name="page" style="width:20px"/>
		<input type="submit" name="go" value="go"/>
		CurrentPage:${sessionScope.currentPageNumber}/${sessionScope.pageTotalNumber}
		<input type="submit" name="showAll" value="showAllSensors" style="margin-left:5px"/>
	</form>
	</div>
</body>
<script type="text/javascript">
var xmlrequest;
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

function getData()
{
	var datas = document.getElementsByName("sensorId");
	var count = datas.length;
	createXMLHttpRequest();
	var uri = "Controller?count="+count;
	xmlrequest.open("GET",uri,true);
 	xmlrequest.onreadystatechange = processResponse; 
	xmlrequest.send(null);
}

document.body.onload = getData;

function processResponse()
{
	if(xmlrequest.readyState == 4)
	{
		if(xmlrequest.status == 200)
		{
			//得到服务器响应
			if(xmlrequest.responseText.match("0 observation update")){
				document.getElementById("newNumber").style.display="none";
			}
			else{
				document.getElementById("newNumber").style.display="block";
				document.getElementById("newNumber").innerHTML=xmlrequest.responseText;
			}
			//设置十秒钟后再次发送请求
			setTimeout("getData()",10000);
		}
	}
}

function getLatestData(){
	document.getElementById("refresh").click();
} 

function addLine(){
	document.getElementById("newNumber").style.background="none repeat scroll 0 0 #FFFDED0";
 	document.getElementById("newNumber").style.textDecoration="underline"; 
}
function resetLine(){
	document.getElementById("newNumber").style.background="none repeat scroll 0 0 #FEFDED";
 	document.getElementById("newNumber").style.textDecoration="none"; 
}

function changeColor(){
	
}
</script>
</html>