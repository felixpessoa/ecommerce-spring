package com.felixsilva.cursomc.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
			
		
		
		
		Calendar cal = Calendar.getInstance();
		 cal.setTime(instanteDoPedido);
		 cal.add(Calendar.DAY_OF_MONTH, 7);
		 pagto.setDataVencimento(instanteDoPedido + 7);
	}
	
}
