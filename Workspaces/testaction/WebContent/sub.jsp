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
String x,y;
int z;
x=request.getParameter("t1");
y=request.getParameter("t2");
z=Integer.parseInt(x)-Integer.parseInt(y);
response.sendRedirect("index.jsp?result="+z);
%>
</body>
</html>