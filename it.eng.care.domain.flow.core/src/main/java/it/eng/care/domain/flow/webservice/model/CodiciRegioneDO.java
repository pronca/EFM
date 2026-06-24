package it.eng.care.domain.flow.webservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FM_CODICI_REGIONE")
public class CodiciRegioneDO {
	
    @Column(name = "CHIAVE")
    @Id
    private String chiave;

    @Column(name = "VALORE")
    private String valore;

    
    
	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

    

}
