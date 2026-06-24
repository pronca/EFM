package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "FM_FLOW_EXTSTD_CONFIG")
public class FmFlowExtstdConfigDO {


	@Id
	@Column(name = "NUMINVIO")
    private String numinvio = "";
	@Column(name = "DATAINIZIO_P2")
	private String datainizioP2;
	@Column(name = "DATAFINE_P2")
	private String datafineP2 = "";
	@Column(name = "DATAINIZIO_P1")
	private String datainizioP1 = "";
	@Column(name = "DATAFINE_P1")
	private String datafineP1 = "";
	@Column(name = "STATO_P1")
	private String statoP1 = "";
	@Column(name = "STATO_P2")
	private String statoP2 = "";
	@Column(name = "DATAINIZIO_P3")
	private String datainizioP3 = "";
	@Column(name = "DATAFINE_P3")
	private String datafineP3 = "";
	@Column(name = "STATO_P3")
	private String statoP3 = "";
	@Column(name = "FLUSSO")
	private String flusso = "";

	public String getNuminvio() {
		return numinvio;
	}

	public void setNuminvio(String numinvio) {
		this.numinvio = numinvio;
	}

	public String getDatainizioP2() {
		return datainizioP2;
	}

	public void setDatainizioP2(String datainizioP2) {
		this.datainizioP2 = datainizioP2;
	}

	public String getDatafineP2() {
		return datafineP2;
	}

	public void setDatafineP2(String datafineP2) {
		this.datafineP2 = datafineP2;
	}

	public String getDatainizioP1() {
		return datainizioP1;
	}

	public void setDatainizioP1(String datainizioP1) {
		this.datainizioP1 = datainizioP1;
	}

	public String getDatafineP1() {
		return datafineP1;
	}

	public void setDatafineP1(String datafineP1) {
		this.datafineP1 = datafineP1;
	}

	public String getStatoP1() {
		return statoP1;
	}

	public void setStatoP1(String statoP1) {
		this.statoP1 = statoP1;
	}

	public String getStatoP2() {
		return statoP2;
	}

	public void setStatoP2(String statoP2) {
		this.statoP2 = statoP2;
	}

	public String getFlusso() {
		return flusso;
	}

	public void setFlusso(String flusso) {
		this.flusso = flusso;
	}

	public String getDatainizioP3() {
		return datainizioP3;
	}

	public void setDatainizioP3(String datainizioP3) {
		this.datainizioP3 = datainizioP3;
	}

	public String getDatafineP3() {
		return datafineP3;
	}

	public void setDatafineP3(String datafineP3) {
		this.datafineP3 = datafineP3;
	}

	public String getStatoP3() {
		return statoP3;
	}

	public void setStatoP3(String statoP3) {
		this.statoP3 = statoP3;
	}
}
