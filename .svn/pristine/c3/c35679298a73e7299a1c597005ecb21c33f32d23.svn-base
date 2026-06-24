/**
 * FlussiFile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * FlussiFile
 *
 * Versione migrata da Axis 1.4 a JAXB Jakarta
 * Compatibile con Java 17 + Spring Boot 3.x
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "flussiFile",
    propOrder = {
        "codTipoFile",
        "codTipoGruppo",
        "dataIns",
        "dimensione",
        "esito",
        "file",
        "formato",
        "nomeFile",
        "nomefileOriginale",
        "numRecord",
        "prgFile"
    }
)
@XmlRootElement(name = "flussiFile", namespace = "http://ws.sis.eng.it/")
public class FlussiFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "codTipoFile")
    private String codTipoFile;

    @XmlElement(name = "codTipoGruppo")
    private String codTipoGruppo;

    @XmlElement(name = "dataIns")
    private String dataIns;

    @XmlElement(name = "dimensione")
    private String dimensione;

    @XmlElement(name = "esito")
    private Esito esito;

    @XmlElement(name = "file")
    private byte[] file;

    @XmlElement(name = "formato")
    private String formato;

    @XmlElement(name = "nomeFile")
    private String nomeFile;

    @XmlElement(name = "nomefileOriginale")
    private String nomefileOriginale;

    @XmlElement(name = "numRecord")
    private Integer numRecord;

    @XmlElement(name = "prgFile")
    private String prgFile;

    // JAXB richiede costruttore vuoto
    public FlussiFile() {
    }

    public FlussiFile(
            String codTipoFile,
            String codTipoGruppo,
            String dataIns,
            String dimensione,
            Esito esito,
            byte[] file,
            String formato,
            String nomeFile,
            String nomefileOriginale,
            Integer numRecord,
            String prgFile) {

        this.codTipoFile = codTipoFile;
        this.codTipoGruppo = codTipoGruppo;
        this.dataIns = dataIns;
        this.dimensione = dimensione;
        this.esito = esito;
        this.file = file;
        this.formato = formato;
        this.nomeFile = nomeFile;
        this.nomefileOriginale = nomefileOriginale;
        this.numRecord = numRecord;
        this.prgFile = prgFile;
    }

    public String getCodTipoFile() {
        return codTipoFile;
    }

    public void setCodTipoFile(String codTipoFile) {
        this.codTipoFile = codTipoFile;
    }

    public String getCodTipoGruppo() {
        return codTipoGruppo;
    }

    public void setCodTipoGruppo(String codTipoGruppo) {
        this.codTipoGruppo = codTipoGruppo;
    }

    public String getDataIns() {
        return dataIns;
    }

    public void setDataIns(String dataIns) {
        this.dataIns = dataIns;
    }

    public String getDimensione() {
        return dimensione;
    }

    public void setDimensione(String dimensione) {
        this.dimensione = dimensione;
    }

    public Esito getEsito() {
        return esito;
    }

    public void setEsito(Esito esito) {
        this.esito = esito;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public String getNomefileOriginale() {
        return nomefileOriginale;
    }

    public void setNomefileOriginale(String nomefileOriginale) {
        this.nomefileOriginale = nomefileOriginale;
    }

    public Integer getNumRecord() {
        return numRecord;
    }

    public void setNumRecord(Integer numRecord) {
        this.numRecord = numRecord;
    }

    public String getPrgFile() {
        return prgFile;
    }

    public void setPrgFile(String prgFile) {
        this.prgFile = prgFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlussiFile)) return false;

        FlussiFile other = (FlussiFile) o;

        return Objects.equals(codTipoFile, other.codTipoFile)
            && Objects.equals(codTipoGruppo, other.codTipoGruppo)
            && Objects.equals(dataIns, other.dataIns)
            && Objects.equals(dimensione, other.dimensione)
            && Objects.equals(esito, other.esito)
            && Arrays.equals(file, other.file)
            && Objects.equals(formato, other.formato)
            && Objects.equals(nomeFile, other.nomeFile)
            && Objects.equals(nomefileOriginale, other.nomefileOriginale)
            && Objects.equals(numRecord, other.numRecord)
            && Objects.equals(prgFile, other.prgFile);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(
            codTipoFile,
            codTipoGruppo,
            dataIns,
            dimensione,
            esito,
            formato,
            nomeFile,
            nomefileOriginale,
            numRecord,
            prgFile
        );
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "FlussiFile{" +
                "codTipoFile='" + codTipoFile + '\'' +
                ", codTipoGruppo='" + codTipoGruppo + '\'' +
                ", dataIns='" + dataIns + '\'' +
                ", dimensione='" + dimensione + '\'' +
                ", esito=" + esito +
                ", file=" + (file != null ? file.length + " bytes" : "null") +
                ", formato='" + formato + '\'' +
                ", nomeFile='" + nomeFile + '\'' +
                ", nomefileOriginale='" + nomefileOriginale + '\'' +
                ", numRecord=" + numRecord +
                ", prgFile='" + prgFile + '\'' +
                '}';
    }
}
