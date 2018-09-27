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
String x2,y2;
int z2;
x2=request.getParameter("x2");
y2=request.getParameter("y2");
z2=Integer.parseInt(x2)-Integer.parseInt(y2);
response.sendRedirect("index.jsp?result2="+z2);
%>

</body>
</html>