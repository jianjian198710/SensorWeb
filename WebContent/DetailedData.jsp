<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>DetailedData</title>
</head>
<body>
	<table>
		<tr>
			<td><label>TimeStamp</label></td>
			<td><label>SensorID</label></td>
			<td><label>ObservableProperty</label></td>
			<td><label>Value</label></td>
			<td><label>Uom</label></td>
		</tr>
		<c:forEach items="${sessionScope.AllDatas}" var="data">
			<tr>
			<td><input type="text" value="${data.timeStamp}" readonly="readonly"/></td>
			<td><input type="text" name="sensorId" value="${data.sensorID}" readonly="readonly"/></td>
			<td><input type="text" value="${data.observableProperty}" readonly="readonly"/></td>
			<td><input type="text" value="${data.value}" readonly="readonly"/></td>
			<td><input type="text" value="${data.uom}" readonly="readonly"/></td>
			</tr>
		</c:forEach>
	</table>
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
	console.log(count);
	createXMLHttpRequest();
	var uri = "Controller?count="+count;
	console.log(uri);
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
/* 			var prices = xmlrequest.responseText.split("$"); */
			//通过FireBug的Console可以看到该日志
			console.log("Good!!!");
/* 			document.getElementById("mysql").innerHTML = prices[0];
			document.getElementById("tomcat").innerHTML = prices[1];
			document.getElementById("jetty").innerHTML = prices[2]; */
			//设置一秒钟后再次发送请求
			setTimeout("getData()",10000);
		}
	}
}

</script>
</html>