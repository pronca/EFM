package it.eng.care.domain.flow.email.utilities;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.google.common.io.Files;

import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.utility.LogUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class EmailUtilitiesSender {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	protected ConfigurationDAO configuration;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtilitiesSender.class);

	public void sendSimpleMessage() {
	}

	public void sendMessageWithAttach(byte[] bytes, String fileName, String[] to, String subject, String text) {

		String from = configuration.findByKeyId("fromEmailError").getValue();

		try {

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true);
			//se non ci sono destiantari non creo il file temporaneo (secondo controllo)

			if (to.length > 0) {
			File filex;
			filex = File.createTempFile("extraction", ".tmp");
	
				Files.write(bytes, filex);
				helper.setTo(to);
				helper.setSubject(subject);
				if (from != null) {
					helper.setFrom(from);
				} else {
					LOGGER.info("FROM EMAIL ASSENTE");
				}
				helper.setText(text);

				FileSystemResource file = new FileSystemResource(filex);
				helper.addAttachment(fileName, file);

				javaMailSender.send(message);
				filex.delete();
			} else {
				LOGGER.info("NESSUN DESTINATARIO CONFIGURATO");

			}

		} catch (MessagingException e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		} catch (IOException e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}
	}

}
