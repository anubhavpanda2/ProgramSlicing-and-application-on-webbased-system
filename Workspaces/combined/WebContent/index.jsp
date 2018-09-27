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
function validateForm1() {
    var x = document.forms["form2"]["x1"].value;
    var y = document.forms["form2"]["y1"].value;
    if(parseInt(x)!=x||parseInt(y)!=y)
    	{
    		alert("Enter Integers only as Input");
    		return false;
    	}
    else
    	return true;
}
function validateForm2() {
    var x = document.forms["form3"]["x2"].value;
    var y = document.forms["form3"]["y2"].value;
    if(parseInt(x)!=x||parseInt(y)!=y)
    	{
    		alert("Enter Integers only as Input");
    		return false;
    	}
    else
    	return true;
}
function validateForm3() {
    var x = document.forms["form4"]["x3"].value;
    var y = document.forms["form4"]["y3"].value;
    if(parseInt(x)!=x||parseInt(y)!=y)
    	{
    		alert("Enter Integers only as Input");
    		return false;
    	}
    else
    	return true;
}
function validateForm4() {
    var x = document.forms["form5"]["x4"].value;
    var y = document.forms["form5"]["y4"].value;
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
Factorial <br/>
<form action="factorial.jsp" method="post" name="form1" >
<input type="text" name="x" /><br>
<input type="submit" name="max" onclick="return validateForm()"/>
</form>
<br/>
Addition
<br/>
<form action="add.jsp" method="post" name="form2" >
<input type="text" name="x1" /><br>
<input type="text" name="y1" /><br>
<input type="submit" name="max2" onclick="return validateForm1()"/>
</form>
<br>
Subtraction
<br/>
<form action="sub.jsp" method="post" name="form3" >
<input type="text" name="x2" /><br>
<input type="text" name="y2" /><br>
<input type="submit" name="max3" onclick="return validateForm2()"/>
</form>
<br>
multiplication
<br/>
<form action="mul.jsp" method="post" name="form4" >
<input type="text" name="x3" /><br>
<input type="text" name="y3" /><br>
<input type="submit" name="max4" onclick="return validateForm3()"/>
</form>
<br>
division
<br/>
<form action="div.jsp" method="post" name="form5" >
<input type="text" name="x4" /><br>
<input type="text" name="y4" /><br>
<input type="submit" name="max5" onclick="return validateForm4()"/>
</form>
<br>

<%
String z4;
z4=request.getParameter("result4");
if(z4!=null)
	out.println("Division:<input type=\"text\" name=\"result4\" value=\""+z4+"\"/>");
%>

<%
String z3;
z3=request.getParameter("result3");
if(z3!=null)
	out.println("multiplication:<input type=\"text\" name=\"result3\" value=\""+z3+"\"/>");
%>

<%
String z2;
z2=request.getParameter("result2");
if(z2!=null)
	out.println("Subtraction:<input type=\"text\" name=\"result2\" value=\""+z2+"\"/>");
%>
<%
String z1;
z1=request.getParameter("result1");
if(z1!=null)
	out.println("Addition:<input type=\"text\" name=\"result1\" value=\""+z1+"\"/>");
%>

<%
String z;
z=request.getParameter("result");
if(z!=null)
	out.println("Factorial:<input type=\"text\" name=\"result\" value=\""+z+"\"/>");
%>
</body>
</html>