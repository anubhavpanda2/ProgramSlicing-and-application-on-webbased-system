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
String x1,y1;
int z1;
x1=request.getParameter("x1");
y1=request.getParameter("y1");
z1=Integer.parseInt(x1)+Integer.parseInt(y1);
response.sendRedirect("index.jsp?result1="+z1);
%>

</body>
</html>