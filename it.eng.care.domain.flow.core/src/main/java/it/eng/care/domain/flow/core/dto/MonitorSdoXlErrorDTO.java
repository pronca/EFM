package it.eng.care.domain.flow.core.dto;

import java.util.Date;

public class MonitorSdoXlErrorDTO {
	
	private String codice;
	private String descrizione;
	private String valoreErato;
	private String campo;
	private Date dateValidazione;
	private String gravita;
	
	public MonitorSdoXlErrorDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getValoreErato() {
		return valoreErato;
	}
	public void setValoreErato(String valoreErato) {
		this.valoreErato = valoreErato;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public Date getDateValidazione() {
		return dateValidazione;
	}
	public void setDateValidazione(Date dateValidazione) {
		this.dateValidazione = dateValidazione;
	}
	public String getGravita() {
		return gravita;
	}
	public void setGravita(String gravita) {
		this.gravita = gravita;
	}
	
	
}
