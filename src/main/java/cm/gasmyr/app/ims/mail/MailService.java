package cm.gasmyr.app.ims.mail;

public interface MailService {

	boolean sendEmail(MessageToSend message);

	boolean sendHtmlMail(MessageToSend message);
}