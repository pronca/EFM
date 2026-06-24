package it.eng.care.domain.flow.email.service;

import jakarta.mail.MessagingException;

public interface MailSender {

	public void sendEmailErrors() throws MessagingException;

	
}
