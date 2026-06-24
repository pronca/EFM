package it.eng.care.domain.flow.core.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "FM_MONITOR_SDO_XL")
@Entity
public class MonitorSdoXlDO {
	
	@Id
	//@GeneratedValue(generator = "care-uuid")
	//@GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "ID_FLUSSO")
	private String flussoId;
	
	@Column(name = "FLUSSO")
	private String flusso;
	
	@Column(name = "PRESIDIO")
	private String presidio;
	
	@Column(name = "AZIENDA")
	private String azienda;
	
	@Column(name = "NOSOLOGICO")
	private String nosologico;
	
	@Column(name = "NOMINATIVO")
	private String nominativo;
	
	@Column(name = "REPARTO")
	private String reparto;
	
	@Column(name = "DATA_RICOVERO")
	private Date dataRicovero;
	
	@Column(name = "DATA_DIMISSIONE")
	private Date dataDimissione;
	
	@Column(name = "OPERAZIONE")
	private String operazione;
	
	@Column(name = "TRASMISSIONE")
	private String trasmissione;
	
	@Column(name = "PROTOCOLLO_SIO")
	private String protocolloSio;
	
	@Column(name = "DATA_INVIO")
	private Date dataInvio;
	
	@Column(name = "DATA_RICEZIONE")
	private Date dataRicezione;
	
	@Column(name = "ESITO")
	private String esito;
	
	@Column(name = "ID_ESTRAZIONE")
	private String idEstrazione;
	
	@Column(name = "DELETED")
	private Boolean deleted;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFlussoId() {
		return flussoId;
	}
	public void setFlussoId(String flussoId) {
		this.flussoId = flussoId;
	}
	public String getFlusso() {
		return flusso;
	}
	public void setFlusso(String flusso) {
		this.flusso = flusso;
	}
	public String getAzienda() {
		return azienda;
	}
	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}
	public String getPresidio() {
		return presidio;
	}
	public void setPresidio(String presidio) {
		this.presidio = presidio;
	}
	public String getNosologico() {
		return nosologico;
	}
	public void setNosologico(String nosologico) {
		this.nosologico = nosologico;
	}
	public String getNominativo() {
		return nominativo;
	}
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	public String getReparto() {
		return reparto;
	}
	public void setReparto(String reparto) {
		this.reparto = reparto;
	}
	public Date getDataRicovero() {
		return dataRicovero;
	}
	public void setDataRicovero(Date dataRicovero) {
		this.dataRicovero = dataRicovero;
	}
	public Date getDataDimissione() {
		return dataDimissione;
	}
	public void setDataDimissione(Date dataDimissione) {
		this.dataDimissione = dataDimissione;
	}
	public String getOperazione() {
		return operazione;
	}
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	public String getTrasmissione() {
		return trasmissione;
	}
	public void setTrasmissione(String trasmissione) {
		this.trasmissione = trasmissione;
	}
	public String getProtocolloSio() {
		return protocolloSio;
	}
	public void setProtocolloSio(String protocolloSio) {
		this.protocolloSio = protocolloSio;
	}
	public Date getDataInvio() {
		return dataInvio;
	}
	public void setDataInvio(Date dataInvio) {
		this.dataInvio = dataInvio;
	}
	public Date getDataRicezione() {
		return dataRicezione;
	}
	public void setDataRicezione(Date dataRicezione) {
		this.dataRicezione = dataRicezione;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public String getIdEstrazione() {
		return idEstrazione;
	}
	public void setIdEstrazione(String idEstrazione) {
		this.idEstrazione = idEstrazione;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}
