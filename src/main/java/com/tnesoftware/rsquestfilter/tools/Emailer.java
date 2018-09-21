package com.tnesoftware.rsquestfilter.tools;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailer {
	
	private Emailer(){}
	
	private static Properties props;
	private static String username;
	private static String password;
	
	static
	{
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		username = "feedbackrsquestfilter@gmail.com";
		password = "1qazxsw2!QAZXSW@";
	}
	
	public static void emailFeedback( String feedback )
	{
		Session session = Session.getInstance( props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication( username, password );
			}
		});
		
		try
		{
			Message message = new MimeMessage(session);
			message.setFrom( new InternetAddress("feedbackrsquestfilter@gmail.com") );
			message.setRecipients( Message.RecipientType.TO, InternetAddress.parse("trentinthomas94@gmail.com,soulsword285@gmail.com") );
			message.setSubject( "Feedback for rsquestfilter" );
			message.setText( feedback );
			
			Transport.send(message);
		}
		catch( MessagingException e )
		{
			e.printStackTrace();
		}
	}

}
