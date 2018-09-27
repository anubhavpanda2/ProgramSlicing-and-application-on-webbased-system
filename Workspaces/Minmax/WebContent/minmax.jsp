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
String x,y,q;
int z;
x=request.getParameter("x");
y=request.getParameter("y");
q=request.getParameter("q");
z=Integer.parseInt(x)+Integer.parseInt(y)*Integer.parseInt(x)/100+Integer.parseInt(q)*Integer.parseInt(x)/100;
response.sendRedirect("index.jsp?result="+z);
%>
</body>
</html>