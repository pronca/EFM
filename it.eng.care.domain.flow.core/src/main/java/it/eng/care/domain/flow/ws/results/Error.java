package it.eng.care.domain.flow.ws.results;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
//import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per error complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="error">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="presidio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nosologico" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idFlusso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codiceErrore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataElaborazione" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="esito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "error")
public class Error {

    @XmlElement(required = true)
    protected String azienda;
    @XmlElement(required = true)
    protected String presidio;
    @XmlElement(required = true)
    protected String nosologico;
    @XmlElement(required = true)
    protected String idFlusso;
    @XmlElement(required = true)
    protected String codiceErrore;
    @XmlElement(required = true)
    protected String descrizione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date dataElaborazione;
    @XmlElement(required = true)
    protected String esito;
    @XmlElement(required = false)
    protected String protocolloSIO;
    @XmlElement(required = false)
    private String drg;
    @XmlElement(required = false)
    private String importo;
    @XmlElement(required = false)
    private String costo;
    

    public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	/**
     * Recupera il valore della proprietà presidio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresidio() {
        return presidio;
    }

    /**
     * Imposta il valore della proprietà presidio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresidio(String value) {
        this.presidio = value;
    }

    /**
     * Recupera il valore della proprietà nosologico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNosologico() {
        return nosologico;
    }

    /**
     * Imposta il valore della proprietà nosologico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNosologico(String value) {
        this.nosologico = value;
    }

    /**
     * Recupera il valore della proprietà idFlusso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlusso() {
        return idFlusso;
    }

    /**
     * Imposta il valore della proprietà idFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlusso(String value) {
        this.idFlusso = value;
    }

    /**
     * Recupera il valore della proprietà codiceErrore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceErrore() {
        return codiceErrore;
    }

    /**
     * Imposta il valore della proprietà codiceErrore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceErrore(String value) {
        this.codiceErrore = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà dataElaborazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDataElaborazione() {
        return dataElaborazione;
    }

    /**
     * Imposta il valore della proprietà dataElaborazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataElaborazione(Date value) {
        this.dataElaborazione = value;
    }

    /**
     * Recupera il valore della proprietà esito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della proprietà esito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsito(String value) {
        this.esito = value;
    }

	public String getProtocolloSIO() {
		return protocolloSIO;
	}

	public void setProtocolloSIO(String protocolloSIO) {
		this.protocolloSIO = protocolloSIO;
	}

	public String getDrg() {
		return drg;
	}

	public void setDrg(String drg) {
		this.drg = drg;
	}

	public String getImporto() {
		return importo;
	}

	public void setImporto(String importo) {
		this.importo = importo;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}
	
	
    
}
