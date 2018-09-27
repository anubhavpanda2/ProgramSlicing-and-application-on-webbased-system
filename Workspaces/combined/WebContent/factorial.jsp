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
int z,i;
x=request.getParameter("x");
z=Integer.parseInt(x);
for(i=z-1;i>1;i--)
	z=z*i;
response.sendRedirect("index.jsp?result="+z);
%>
</body>
</html>