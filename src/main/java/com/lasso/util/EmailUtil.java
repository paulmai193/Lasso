/*
 * 
 */
package com.lasso.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.core.io.Resource;

import com.lasso.rest.service.GenericManagement;

/**
 * The Class EmailUtil.
 *
 * @author Paul Mai
 */
public final class EmailUtil {

	/** The generic management. */
	private GenericManagement	genericManagement;

	/** The password. */
	private String				password;

	/** The property file. */
	private Resource			propertyFile;

	/** The session. */
	private Session				session;

	/** The username. */
	private String				username;

	/**
	 * Instantiates a new email util.
	 */
	public EmailUtil() {
	}

	/**
	 * Send email.
	 *
	 * @param __subject the subject
	 * @param __content the content
	 * @param __attachment the attachment
	 * @param __recipients the map recipients group by type: TO, CC, BCC. List email seperate by ","
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public synchronized void sendEmail(String __subject, String __content, File __attachment,
			Map<RecipientType, String> __recipients) throws AddressException, MessagingException {
		List<File> _attachments = new ArrayList<>(1);
		_attachments.add(__attachment);
		this.sendEmail(__subject, __content, _attachments, __recipients);
	}

	/**
	 * Send email.
	 *
	 * @param __subject the subject
	 * @param __content the content
	 * @param __attachments the list attachments
	 * @param __recipients the map recipients group by type: TO, CC, BCC. List email seperate by ","
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public synchronized void sendEmail(String __subject, String __content, List<File> __attachments,
			Map<RecipientType, String> __recipients) throws AddressException, MessagingException {

		// Create a default MimeMessage object.
		MimeMessage _message = new MimeMessage(this.session);

		_message.setFrom(new InternetAddress(this.username));
		for (Entry<RecipientType, String> _element : __recipients.entrySet()) {
			_message.setRecipients(_element.getKey(), InternetAddress.parse(_element.getValue()));
		}
		_message.setSubject(__subject, "UTF-8");

		Multipart _multipart = new MimeMultipart();

		BodyPart _messageBodyPart = new MimeBodyPart();
		_messageBodyPart.setContent(__content, "text/html; charset=UTF-8");
		_multipart.addBodyPart(_messageBodyPart);

		for (File _file : __attachments) {
			this.attachFile(_multipart, _file);
		}

		_message.setContent(_multipart);

		Transport.send(_message);
	}

	/**
	 * Send email.
	 *
	 * @param __subject the subject
	 * @param __content the content
	 * @param __recipients the map recipients group by type: TO, CC, BCC. List email seperate by ","
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public synchronized void sendEmail(String __subject, String __content,
			Map<RecipientType, String> __recipients) throws AddressException, MessagingException {

		MimeMessage _message = new MimeMessage(this.session);

		_message.setFrom(new InternetAddress(this.username));
		for (Entry<RecipientType, String> _element : __recipients.entrySet()) {
			_message.setRecipients(_element.getKey(), InternetAddress.parse(_element.getValue()));
		}
		_message.setSubject(__subject, "UTF-8");
		_message.setContent(__content, "text/html; charset=UTF-8");

		Transport.send(_message);
	}

	/**
	 * Send email.
	 *
	 * @param __recipients the recipients
	 * @param __subject the subject
	 * @param __content the content
	 * @param __attachment the attachment file
	 * @param __receipientType the receipient type
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public synchronized void sendEmail(String __recipients, String __subject, String __content,
			File __attachment, RecipientType __receipientType)
					throws AddressException, MessagingException {
		List<File> _attachments = new ArrayList<>(1);
		_attachments.add(__attachment);
		this.sendEmail(__recipients, __subject, __content, _attachments, __receipientType);
	}

	/**
	 * Send email.
	 *
	 * @param __recipients the recipients
	 * @param __subject the subject
	 * @param __content the content
	 * @param __attachments the list of attachment files
	 * @param __receipientType the receipient type
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public synchronized void sendEmail(String __recipients, String __subject, String __content,
			List<File> __attachments, RecipientType __receipientType)
					throws AddressException, MessagingException {
		Map<RecipientType, String> _mapRecipients = new HashMap<>(1);
		_mapRecipients.put(__receipientType, __recipients);
		this.sendEmail(__subject, __content, __attachments, _mapRecipients);
	}

	/**
	 * Send email.
	 *
	 * @param __recipients the recipients
	 * @param __subject the subject
	 * @param __content the content
	 * @param __recipientType the recipient type (TO, CC, BCC)
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public synchronized void sendEmail(String __recipients, String __subject, String __content,
			RecipientType __recipientType) throws AddressException, MessagingException {
		Map<RecipientType, String> _mapRecipients = new HashMap<>(1);
		_mapRecipients.put(__recipientType, __recipients);
		this.sendEmail(__subject, __content, _mapRecipients);
	}

	/**
	 * Sets the generic management.
	 *
	 * @param __genericManagement the new generic management
	 */
	public void setGenericManagement(GenericManagement __genericManagement) {
		this.genericManagement = __genericManagement;
	}

	/**
	 * Sets the property file.
	 *
	 * @param __propertyFile the new property file
	 */
	public void setPropertyFile(final Resource __propertyFile) {
		this.propertyFile = __propertyFile;
	}

	/**
	 * Attach file.
	 *
	 * @param __multipart the multipart
	 * @param __file the file
	 * @throws MessagingException the messaging exception
	 */
	private synchronized void attachFile(Multipart __multipart, File __file)
			throws MessagingException {
		BodyPart _attachPart = new MimeBodyPart();
		DataSource _source = new FileDataSource(__file);
		_attachPart.setDataHandler(new DataHandler(_source));
		_attachPart.setFileName(__file.getName());
		__multipart.addBodyPart(_attachPart);
	}

	/**
	 * Initialized mail session.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the URI syntax exception
	 */
	@SuppressWarnings("unused")
	private void initialized() throws FileNotFoundException, IOException, URISyntaxException {
		// Properties _props = new Properties();
		// _props.load(this.propertyFile.getInputStream());
		// this.username = _props.containsKey("email.username") ?
		// _props.getProperty("email.username")
		// : "n/a";
		// this.password = _props.containsKey("email.password") ?
		// _props.getProperty("email.password")
		// : "n/a";

		Map<String, String> _mapConfig = this.genericManagement.loadConfig();
		this.username = _mapConfig.get("EmailConfig.email_username");
		this.password = _mapConfig.get("EmailConfig.email_password");
		Properties _props = new Properties();
		_props.setProperty("mail.smtp.auth", "true");
		String _emailHost = _mapConfig.get("EmailConfig.email_host");
		if (_emailHost.contains("ssl://")) {
			_emailHost = _emailHost.replace("ssl://", "");
			_props.setProperty("mail.smtp.ssl.enable", "true");
			_props.setProperty("mail.smtp.socketFactory.port",
					_mapConfig.get("EmailConfig.email_port"));
			_props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		else {
			_props.setProperty("mail.smtp.starttls.enable", "true");
		}
		_props.setProperty("mail.smtp.host", _emailHost);
		_props.setProperty("mail.smtp.port", _mapConfig.get("EmailConfig.email_port"));

		this.session = Session.getInstance(_props, new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailUtil.this.username, EmailUtil.this.password);
			}
		});
	}

}
