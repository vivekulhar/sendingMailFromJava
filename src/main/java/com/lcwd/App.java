package com.lcwd;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "prepareing to send message ..." );
        String message = "Hello, Dear, this is message for security check.";
        String subject="LearningEmail:Confirmation";
        String to = "vivek.kulhar23@gmail.com";
        String from = "learnbackendvivek@gmail.com";
        
//      sendEmail(message, subject, to, from);
    
        sendAttach(message, subject, to, from);
        
    }
    
    //this is responsible to send the message with attachment
    private static void sendAttach(String message, String subject, String to, String from) {

    	
    	// Variable for gmail 
		
    			String host = "smtp.gmail.com";
    			
    			//get the system properties
    			
    			Properties properties = System.getProperties();
    			System.out.println("PROPERTIES"+properties);
    			
    			//setting important information to properties object
    			
    			
    			//host set--key value pair, mail.smtp.host is key that API will use
    			properties.put("mail.smtp.host", host);
    			properties.put("mail.smtp.port", "465");
    			properties.put("mail.smtp.ssl.enable", "true");
    			properties.put("mail.smtp.auth", "true");
    		
    			//Step 1: to get the session object..
    			Session session = Session.getInstance(properties, new Authenticator() {

    				@Override
    				protected PasswordAuthentication getPasswordAuthentication() {
    					//I have created an App Password here
    					return new PasswordAuthentication("learnbackendvivek@gmail.com", "dpfjbtsfynqlvxgt");
    				}
    			});
    			
    			session.setDebug(true);
    			
    			//Step 2: Compose the message [text,multi media]
    			
    			MimeMessage m = new MimeMessage(session);
    			
    			try {
    				//from email
    				m.setFrom(from);
    				
    				//adding recipient to message
    				m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    				
    				//adding subject to message
    				m.setSubject(subject);
    				
    				//adding text to message
//    				m.setText(message);
    				
    				//Attachment..
    				
    				//file path
    				String path = "C:\\Users\\Welcome\\Downloads\\Mickey_Mouse.png";
    				
    				MimeMultipart mimeMultipart = new MimeMultipart();
    				//text
    				MimeBodyPart textMime = new MimeBodyPart();
    				
    				//file
    				MimeBodyPart fileMime = new MimeBodyPart();
    				
    				try {
						textMime.setText(message);
						
						File file = new File(path);
						fileMime.attachFile(file);
						
						mimeMultipart.addBodyPart(textMime);
						mimeMultipart.addBodyPart(fileMime);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
    				
    				m.setContent(mimeMultipart);
    				
    				//send
    				
//    				Step 3: send the message using Transport class
    				Transport.send(m);
    				
    				System.out.println("Send success.......");
    				
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
		
	}
	// this method is responsible to send email..
	private static void sendEmail(String message, String subject, String to, String from) {
		
		// Variable for gmail 
		
		String host = "smtp.gmail.com";
		
		//get the system properties
		
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES"+properties);
		
		//setting important information to properties object
		
		
		//host set--key value pair, mail.smtp.host is key that API will use
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
	
		//Step 1: to get the session object..
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//I have created an App Password here
				return new PasswordAuthentication("learnbackendvivek@gmail.com", "dpfjbtsfynqlvxgt");
			}
		});
		
		session.setDebug(true);
		
		//Step 2: Compose the message [text,multi media]
		
		MimeMessage m = new MimeMessage(session);
		
		try {
			//from email
			m.setFrom(from);
			
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);
			
			//send
			
//			Step 3: send the message using Transport class
			Transport.send(m);
			
			System.out.println("Send success.......");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
