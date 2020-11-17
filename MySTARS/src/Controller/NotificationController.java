package Controller;
import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;  

import Model.Student;

public class NotificationController {
	public static void sendNotification(Student student, String courseCode) {
		sendEmail(student,courseCode);
	}
	/**
	 * Send email for wait list notification
	 * @param student
	 */
	public static void sendEmail(Student student, String courseCode)
	{
		String emailTo = student.getEmail();
		String studentName = student.getName();
		String emailFrom = "ss9cz2002grp9@gmail.com";
		String password = "abc12345?";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(emailFrom, password);
					}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailFrom));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo)); // to be added an email addr
			message.setSubject("Testing Subject");
			message.setText("Dear " + studentName
				+ "\n\n You have successfuly registered" + courseCode);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println("failed");
			throw new RuntimeException(e);
		}
		
	    


	}
	

}
