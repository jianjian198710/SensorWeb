<% String property = request.getParameter("q");
	if(property.equalsIgnoreCase("t")){
		response.getWriter().write("Temperature");
	}
%>