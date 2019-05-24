package libraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import utilities.Constants;
import utilities.TestBase;

public class EmailReport extends TestBase {

	/**
	 * @author Samrat
	 * @category Email function
	 * @throws EmailException
	 * @throws IOException
	 */
	public static void sendEmailOnComplete() throws EmailException, IOException {
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH);
			prop.load(fis);
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(Constants.EMAIL_ATTACH_PATH);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Extent Reports");
			attachment.setName(prop.getProperty("applicationName"));
			log.info("Email attachment details have been set for application: " + prop.getProperty("applicationName"));
			
			
			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(prop.getProperty("hostName"));
			int smtpPort = Integer.parseInt(prop.getProperty("smtpPort"));
			email.setSmtpPort(smtpPort);
			email.setAuthenticator(
					new DefaultAuthenticator(prop.getProperty("emailUsername"), prop.getProperty("emailPassword")));
			email.setSSLOnConnect(true);
			log.info("Email has been created.");
			
			// Give the Recepient details here
			email.addTo(prop.getProperty("emailTo"), prop.getProperty("emailToName"));
			email.setFrom(prop.getProperty("emailFrom"), prop.getProperty("emailFromName"));
			email.setSubject("Test Execution Results");
			email.setMsg("PFA the test results of the last run");
			email.attach(attachment);
			email.send();
			log.info("Email has been sent to: " + prop.getProperty("emailToName"));
		} catch (NumberFormatException e) {
			log.error("Error occurred while trying to send email " + e.getMessage());
			e.printStackTrace();
		}
	}
}
