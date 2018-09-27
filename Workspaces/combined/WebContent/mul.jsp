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
String x3,y3;
int z3;
x3=request.getParameter("x3");
y3=request.getParameter("y3");
z3=Integer.parseInt(x3)*Integer.parseInt(y3);
response.sendRedirect("index.jsp?result3="+z3);
%>
</body>
</html>