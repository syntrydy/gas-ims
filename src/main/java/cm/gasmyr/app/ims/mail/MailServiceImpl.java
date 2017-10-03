package cm.gasmyr.app.ims.mail;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	final JavaMailSender mailSender;

	@Autowired
	public MailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public boolean sendEmail(MessageToSend message) {
		boolean result = false;
		MimeMessagePreparator preparator = getMessagePreparator(message);
		try {
			mailSender.send(preparator);
			result = true;
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
		return result;
	}

	private MimeMessagePreparator getMessagePreparator(final MessageToSend message) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom(message.getFrom());
				mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(message.getTo()));
				mimeMessage.setText(message.getText());
				mimeMessage.setSubject(message.getSubject());
			}
		};
		return preparator;
	}

	@Override
	public boolean sendHtmlMail(MessageToSend mesg) {
		boolean result = false;
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(mesg.getTo());
			helper.setReplyTo(mesg.getFrom());
			helper.setFrom(mesg.getFrom());
			helper.setSubject(mesg.getSubject());
			helper.setText(mesg.getText(), true);
			mailSender.send(message);
			result = true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return result;
	}

}