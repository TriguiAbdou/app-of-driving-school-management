/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karhabty_pidev_entites;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Oumaima
 */
public class Email extends Authenticator{
  public static boolean Email(String recepteur, String sujet,String msg) throws MessagingException{
		
	boolean test = false;
		final String username = "karhabty1234.karhabty@gmail.com";
		final String password = "07468678";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                       
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("karhabty1234.karhabty@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recepteur));
			message.setSubject(sujet);
			message.setText(msg);
			Transport.send(message);
                        test = true;
 
 
		
		  return test;
	}
   
}
