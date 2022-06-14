package com.felixsilva.cursomc.domain.service;

import org.springframework.mail.SimpleMailMessage;

import com.felixsilva.cursomc.domain.model.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
