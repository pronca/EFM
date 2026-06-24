package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="inviaESimula", propOrder={"token","codTipoFlusso","anno","numInvio","files"})
@XmlRootElement(name = "inviaESimula", namespace = "http://ws.sis.eng.it/")
public class InviaESimulaRequest {

    private String token;
    private String codTipoFlusso;
    private int anno;
    private int numInvio;

    @XmlElement(name = "files")
    private FlussiFile[] files;

    public InviaESimulaRequest() {}

    public InviaESimulaRequest(
            String token,
            String codTipoFlusso,
            int anno,
            int numInvio,
            FlussiFile[] files) {

        this.token = token;
        this.codTipoFlusso = codTipoFlusso;
        this.anno = anno;
        this.numInvio = numInvio;
        this.files = files;
    }
}

