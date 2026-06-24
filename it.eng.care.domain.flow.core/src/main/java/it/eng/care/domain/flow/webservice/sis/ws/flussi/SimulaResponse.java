package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "simulaResponse", namespace = "http://ws.sis.eng.it/")
public class SimulaResponse {

  @XmlElement(name = "return")
  private Esito returnValue;

  public Esito getReturnValue() { return returnValue; }
  public void setReturnValue(Esito returnValue) { this.returnValue = returnValue; }
}
