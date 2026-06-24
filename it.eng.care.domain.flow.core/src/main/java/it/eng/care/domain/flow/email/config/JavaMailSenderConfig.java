package it.eng.care.domain.flow.email.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import it.eng.care.domain.flow.email.service.MailSender;
import it.eng.care.domain.flow.email.service.impl.MailSenderImpl;
import it.eng.care.domain.flow.email.utilities.EmailUtilitiesSender;

@Configuration
public class JavaMailSenderConfig {

	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private String port;
	@Value("${spring.mail.username:''}")
	private String username;
	@Value("${spring.mail.password:''}")
	private String password;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String auth;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String enable;

	@Value("${spring.mail.properties.mail.transport.protocol}")
	private String protocol;

	@Value("${spring.mail.properties.mail.debug}")
	private String debug;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(Integer.parseInt(port));
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", protocol);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", enable);
		props.put("mail.debug", debug);

		return mailSender;
	}

	@Bean
	public EmailUtilitiesSender emailUtilitiesSender() {
		return new EmailUtilitiesSender();
	}

	@Bean
	public MailSender mailSender() {
		return new MailSenderImpl();
	}

}
