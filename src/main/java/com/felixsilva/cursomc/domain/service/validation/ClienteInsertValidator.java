package com.felixsilva.cursomc.domain.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.felixsilva.cursomc.api.controller.exception.FieldMessage;
import com.felixsilva.cursomc.domain.model.enums.TipoCliente;
import com.felixsilva.cursomc.domain.service.validation.utils.BR;
import com.felixsilva.cursomc.dto.ClienteNewDTO;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{

	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
				.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}
