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

/**
 * This is the abstract class Notification. 
 * @author Ray Myat
 *
 */
abstract class NotificationController {
	public abstract void sendNotification(Student student, String courseCode);

}
