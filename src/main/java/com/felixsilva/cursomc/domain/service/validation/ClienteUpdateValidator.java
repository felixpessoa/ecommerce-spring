package com.felixsilva.cursomc.domain.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.felixsilva.cursomc.api.controller.exception.FieldMessage;
import com.felixsilva.cursomc.domain.model.Cliente;
import com.felixsilva.cursomc.domain.model.enums.TipoCliente;
import com.felixsilva.cursomc.domain.repository.ClienteRepository;
import com.felixsilva.cursomc.domain.service.validation.utils.BR;
import com.felixsilva.cursomc.dto.ClienteDTO;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
		
	}
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if ( aux != null && !aux.getClienteId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existemte"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
				.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}
