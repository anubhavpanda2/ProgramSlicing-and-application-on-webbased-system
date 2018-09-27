<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String x4,y4;
int z4;
x4=request.getParameter("x4");
y4=request.getParameter("y4");
z4=Integer.parseInt(x4)/Integer.parseInt(y4);
response.sendRedirect("index.jsp?result4="+z4);
%>
</body>
</html>