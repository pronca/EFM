package it.eng.care.domain.flow.core.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import it.eng.care.platform.persistence.api.IBaseEntity;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Table(name = "FM_ANAGRAFICA_ASSISTITO")
@ToString
public class AnagraficaAssistitoDO implements IBaseEntity,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "care-uuid")
	@GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "COGNOME")
	private String cognome;
	
	@Column(name = "DATANASCITA")
	private Date   datanascita;
	
	@Column(name = "COMUNENASCITA")
	private String comunenascita;
	
	@Column(name = "SESSO")
	private String sesso;
	
	@Column(name = "CODICEFISCALE")
	private String codiceFiscale;
	
	@Column(name = "CODICEPAZIENTE")
    private String codicePaziente;
   
    @Column(name = "COMUNERESIDENZA")
    private String comuneResidenza;
   
    @Column(name = "NAZIONALITA")
    private String nazionalita;
   
    @Column(name = "ABILITAZIONE")
    private Byte abilitazione;
   
    @Column(name = "ASLRESIDENZA")
    private String aslResidenza;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDatanascita() {
		return datanascita;
	}
	public void setDatanascita(Date datanascita) {
		this.datanascita = datanascita;
	}
	public String getComunenascita() {
		return comunenascita;
	}
	public void setComunenascita(String comunenascita) {
		this.comunenascita = comunenascita;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCodicePaziente() {
		return codicePaziente;
	}
	public void setCodicePaziente(String codicePaziente) {
		this.codicePaziente = codicePaziente;
	}
	public String getComuneResidenza() {
		return comuneResidenza;
	}
	public void setComuneResidenza(String comunerezidenza) {
		this.comuneResidenza = comunerezidenza;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	public Byte getAbilitazione() {
		return abilitazione;
	}
	public void setAbilitazione(Byte abilitazione) {
		this.abilitazione = abilitazione;
	}
	public String getAslResidenza() {
		return aslResidenza;
	}
	public void setAslResidenza(String aslResidenza) {
		this.aslResidenza = aslResidenza;
	}
	
	
	
	
	
}
