<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<SCRIPT>
function validateForm() {
    var x = document.forms["theForm"]["t1"].value;
    var y = document.forms["theForm"]["t2"].value;
    if(parseInt(x)!=x||parseInt(y)!=y)
    	{
    		alert("Enter Integers only as Input");
    		return false;
    	}
}
function submitFunction(i) {
   if (i==1) document.theForm.action=
      "add.jsp";
   if (i==2) document.theForm.action=
      "sub.jsp";
   if (i==3) document.theForm.action=
      "mul.jsp";
   if(validateForm())
   document.theForm.submit()
   
   }
</SCRIPT>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<FORM NAME="theForm" method="post" onsubmit="return validateForm()">

<input type ="text" name="t1"/><br/>
<input type ="text" name="t2"/><br/><br/>
<INPUT TYPE="button" VALUE="add" onClick="submitFunction(1)"/>&nbsp;
<INPUT TYPE="button" VALUE="Subtract" onClick="submitFunction(2)"/>&nbsp;
<INPUT TYPE="button" VALUE="multiply" onClick="submitFunction(3)"/><br/>
</FORM>
<%
String z;
z=request.getParameter("result");
if(z!=null)
	out.println("<input type=\"text\" name=\"result\" value=\""+z+"\"/>");
	
	%>

</body>
</html>