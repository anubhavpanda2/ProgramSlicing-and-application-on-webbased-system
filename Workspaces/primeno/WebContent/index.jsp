<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function validateForm() {
    var x = document.forms["form1"]["x"].value;
    if(parseInt(x)!=x||parseInt(y)!=y || parseInt(q)!=q)
    	{
    		alert("Enter Integers only as Input");
    		return false;
    	}
    else
    	return true;
}
</script>
</head>
<body>
<form action="factorial.jsp" method="post" name="form1" >
<input type="text" name="x" /><br>
<input type="submit" name="max" onclick="return validateForm()"/>
</form>
</br>
<%
String z;
z=request.getParameter("result");
if(z!=null)
	out.println("<input type=\"text\" name=\"result\" value=\""+z+"\"/>");
%>
</body>
</html>