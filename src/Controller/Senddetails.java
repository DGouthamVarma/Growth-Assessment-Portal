package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ForgotUsernameAndPassword;
import DAO.ForgotDAO;

/**
 * Servlet implementation class SendUserName
 */
@WebServlet("/SendUserName")
public class Senddetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Senddetails() {
        super();
    }
    
    private static String subjectPasswordChange = "Growth Assessment Portal Password";
    private static String messagePasswordChange = "Dear User,\nBelow is your password for Growth Assessment Portal. Please remember your login credentials.\n\nDon't share password with anyone. Happy Growing!\n\nPassword: ";
    private static String signature = "Best Regards,\nTeam Developers,\nGrowth Assessment Portal.";
    public static void SendUsername(String senderAddress,String senderPassword,String recipientAddress,String subject,String emailMessage){  
        Properties hostProperties = new Properties();    
        hostProperties.put("mail.smtp.host", "smtp.gmail.com");    
        hostProperties.put("mail.smtp.socketFactory.port", "465");    
        hostProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
        hostProperties.put("mail.smtp.auth", "true");    
        hostProperties.put("mail.smtp.port", "465");    

        Session session = Session.getDefaultInstance(hostProperties, new javax.mail.Authenticator() {  
      	  protected PasswordAuthentication getPasswordAuthentication() {    
      		  return new PasswordAuthentication(senderAddress,senderPassword);  
      	  }    
        });

        try {    
         MimeMessage message = new MimeMessage(session); 
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipientAddress)); 
         message.setSubject(subject);  
         message.setText(emailMessage); 
         Transport.send(message); 
         System.out.println("message sent successfully");    
        } 
        catch (MessagingException sendingError) {
        	System.out.println("Error is in sendingEmail method");
      	  throw new RuntimeException(sendingError);
        }          
 }  
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEmail = request.getParameter("emailuserchange");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ForgotUsernameAndPassword forgotCredentialsBean = new ForgotUsernameAndPassword();
		forgotCredentialsBean.setUserEmail(userEmail);
		
		ForgotDAO forgotEmailDao = new ForgotDAO();
		String user = forgotEmailDao.validate(forgotCredentialsBean);
		
		if(!user.equals("none")) {
			String password = forgotEmailDao.getPassword(forgotCredentialsBean, user);
			SendUsername("gap.talentsculptors@gmail.com","gapts@444", userEmail, subjectPasswordChange, messagePasswordChange+password+"\n\n"+signature);
			out.write("<h4 style=color:green>We sent you an email</h4>");
		}
		else {
			out.write("<h4 style=color:red>You are not registered yet. Please contact admin</h4>");
		}
	}         
}

