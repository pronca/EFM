package it.eng.care.domain.flow.webservice.service;

import java.math.BigDecimal;

import it.eng.care.domain.flow.webservice.sis.ws.flussi.FlussiFile;

/**
 * 
 * @author mpirozzi
 * 
 * interfaccia necessaria alla comunicazione   presso la regione ER
 */
public interface WebServiceSender {
	
	Object[] consolida(String token, BigDecimal prgInvio, String note, String requestId, boolean drg);

	void delete(String token, BigDecimal prgInvio);

	void checkState(String token, BigDecimal prgInvio);

	String[] login(String requestId, boolean drg);

	Object[] sendFlow(String codTipoFlusso, int anno, int numInvio, String token, FlussiFile[] files,
			String requestId, boolean drg);

	Object[] simulate(String token, BigDecimal prgInvio, String requestId, boolean drg) throws InterruptedException;

}
