package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "invioFile",
    propOrder = { "token", "codTipoFlusso", "anno", "numInvio", "files" }
)
@XmlRootElement(name = "invioFile", namespace = "http://ws.sis.eng.it/")
public class InvioFileRequest {

    @XmlElement(name = "token")
    private String token;

    @XmlElement(name = "codTipoFlusso")
    private String codTipoFlusso;

    @XmlElement(name = "anno")
    private int anno;

    @XmlElement(name = "numInvio")
    private int numInvio;

    // Nel WSDL: name="files" maxOccurs="unbounded" type="tns:flussiFile"
    @XmlElement(name = "files")
    private List<FlussiFile> files = new ArrayList<>();

    public InvioFileRequest() {
    }

    public InvioFileRequest(String token, String codTipoFlusso, int anno, int numInvio, FlussiFile[] files) {
        this.token = token;
        this.codTipoFlusso = codTipoFlusso;
        this.anno = anno;
        this.numInvio = numInvio;
        if (files != null) {
            this.files = new ArrayList<>(Arrays.asList(files));
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCodTipoFlusso() {
        return codTipoFlusso;
    }

    public void setCodTipoFlusso(String codTipoFlusso) {
        this.codTipoFlusso = codTipoFlusso;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getNumInvio() {
        return numInvio;
    }

    public void setNumInvio(int numInvio) {
        this.numInvio = numInvio;
    }

    public List<FlussiFile> getFiles() {
        if (files == null) {
            files = new ArrayList<>();
        }
        return files;
    }

    public void setFiles(List<FlussiFile> files) {
        this.files = files;
    }

    public void setFilesArray(FlussiFile[] files) {
        this.files = (files == null) ? new ArrayList<>() : new ArrayList<>(Arrays.asList(files));
    }
}
