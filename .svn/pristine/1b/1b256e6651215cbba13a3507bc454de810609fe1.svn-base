package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ritornoInformativoSimulazioneResponse", namespace = "http://ws.sis.eng.it/")
public class RitornoInformativoSimulazioneResponse {

  @XmlElement(name = "return")
  private List<FlussiFile> returnValues = new ArrayList<>();

  public List<FlussiFile> getReturnValues() { return returnValues; }
  public void setReturnValues(List<FlussiFile> returnValues) { this.returnValues = returnValues; }
}
