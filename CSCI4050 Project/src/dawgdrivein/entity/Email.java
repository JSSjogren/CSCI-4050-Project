package dawgdrivein.entity;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public void verificationEmail(User user)
	{
		// Recipient's email ID needs to be mentioned.
		String to = user.getEmail();

		// Sender's email ID needs to be mentioned
		String from = "dawgdrivein@gmail.com";
		final String username = "dawgdrivein";//change accordingly
		final String password = "csci4050spring";//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("[Dawg Drive In] Please verify your email!");

			// Now set the actual message
			message.setText("Hello " + user.getFirstName() +  ", \n\nPlease verify your email with the code: 1234\n\n Regards,\nDawg Drive In Development Team");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public String temporaryPasswordEmail(String userEmail)
	{
		// Recipient's email ID needs to be mentioned.
		String to = userEmail;

		// Sender's email ID needs to be mentioned
		String from = "dawgdrivein@gmail.com";
		final String username = "dawgdrivein";//change accordingly
		final String password = "csci4050spring";//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("[Dawg Drive In] Password Recovery");


			String tempPassword = generateRandomPassword();

			// Now set the actual message
			message.setText("Hello, \n\nYour temporary password is: " + tempPassword + ". Please login using this password to enter the change password form.\n\n Regards,\nDawg Drive In Development Team");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

			return tempPassword;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void orderConfirmation(int bookingNo, String email, String firstName, String lastName, String total, String movieSelected, String seating, int numSeats, int numSpaces, String timeSelected, String dateSelected)
	{
		// Recipient's email ID needs to be mentioned.
		String to = email;

		// Sender's email ID needs to be mentioned
		String from = "dawgdrivein@gmail.com";
		final String username = "dawgdrivein";//change accordingly
		final String password = "csci4050spring";//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("[Dawg Drive In] Order #" + bookingNo + " Summary");

			// Now set the actual message
			message.setText("Hello " + firstName +  ", \n\nHere is your order summary: \n"
					+ "Name: " + firstName + " " + lastName + "\n"
					+ "Email: " + email + "\n"
					+ "Movie: " + movieSelected + "\n"
					+ "Date & Time: " + dateSelected + " " + timeSelected + "\n"
					+ "Parking Spot: " + seating + "\n"
					+ "Total: $" + total + '\n'
					+ "\n Regards,\nDawg Drive In Development Team");

			// Send message
			Transport.send(message);

			System.out.println("Sent order summary message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendPromoEmails(Promotion promo)
	{
		User user = new User();
		ArrayList<String> emails = user.retrieveSubscribedUserEmails();
		
		// Sender's email ID needs to be mentioned
		String from = "dawgdrivein@gmail.com";
		final String username = "dawgdrivein";//change accordingly
		final String password = "csci4050spring";//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		for (String email : emails)
		{
			// Recipient's email ID needs to be mentioned.
			String to = email;

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));

				// Set Subject: header field
				message.setSubject("[Dawg Drive In] New Promotion!");

				String someHtmlMessage = ("Hello! <br><br>Dawg Drive In has a new promotion available for subscribed users!" 
										+ " Just enter the code <b>" + promo.getCode() + "</b> at checkout for a <b>" + promo.getPercent_discount() + "%</b> discount!<br><br>"
										+ "Offer expires <b>" + promo.getExp_date() +"</b>.<br><br>"
										+ "Regards,<br>"
										+ "Dawg Drive In Development Team");

				// Now set the actual message
				message.setContent(someHtmlMessage, "text/html; charset=utf-8");
				
				// Send message
				Transport.send(message);

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println("Sent promotion emails successfully....");
	}

	public static String generateRandomPassword()
	{
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";
		Random random = new Random();

		String password = "";
		for (int i=0; i<10; i++)
		{
			int index = (int)(random.nextDouble()*letters.length());
			password += letters.substring(index, index+1);
		}
		return password;
	}

}