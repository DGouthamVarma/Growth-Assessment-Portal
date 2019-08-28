<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="java.sql.*" %>   
<%
String searchtext = request.getParameter("text");
try{
	Class.forName("oracle.jdbc.driver.OracleDriver");  
	Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","444");  
	PreparedStatement statement = connection.prepareStatement("select trainee_id, trainee_name from trainee where trainee_name like '"+searchtext+"%'");
	ResultSet result = statement.executeQuery();
	if(result.isBeforeFirst()) {      
		out.print("<table>"); 
		while(result.next()){  
			out.print("<tr><td><a target='_blank' href='traineeProgress?id="+result.getString(1)+"'>"+result.getString(2)+"</a></td></tr>");  
		}  
			out.print("</table>"); 
	}
	connection.close();  
}
catch(Exception e)
{
	out.write("Error in fetching");
} 
			 
%>