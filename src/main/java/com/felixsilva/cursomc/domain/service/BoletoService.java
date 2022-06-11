package com.felixsilva.cursomc.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, LocalDateTime instanteDoPedido) {
		
		Date dat = Date.from( instanteDoPedido.atZone( ZoneId.systemDefault() ).toInstant() );
		
		Calendar cal = Calendar.getInstance();
		 cal.setTime(dat);
		 cal.add(Calendar.DAY_OF_MONTH, 7);
		 LocalDate localDateTime = cal.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
		 pagto.setDataVencimento(localDateTime);
	}
	
}
