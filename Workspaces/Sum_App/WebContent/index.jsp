<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<script>
function validateForm() {
    var x = document.forms["form1"]["x"].value;
    var y = document.forms["form1"]["y"].value;
    if(parseInt(x)!=x||parseInt(y)!=y)
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
<form action="calc.jsp" method="post" name="form1" >
<input type="text" name="x" /><br>
<input type="text" name="y" /><br>
<input type="submit" name="result" onclick="return validateForm()"/>
</form>
<br>
<%
String z;
z=request.getParameter("result");
if(z!=null)
	out.println("<input type=\"text\" name=\"result\" value=\""+z+"\"/>");
%>
</body>
</html>