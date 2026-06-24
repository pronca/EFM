package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "FlussiWsException",
    propOrder = { "errori", "message" }
)
@XmlRootElement(
    name = "FlussiWsException",
    namespace = "http://ws.sis.eng.it/"
)
public class FlussiWsException implements Serializable {

  private static final long serialVersionUID = 1L;

  @XmlElement(name = "errori")
  private List<String> errori;

  @XmlElement(name = "message")
  private String message;

  public List<String> getErrori() {
    if (errori == null) errori = new ArrayList<>();
    return errori;
  }

  public void setErrori(List<String> errori) {
    this.errori = errori;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "FlussiWsException{message='" + message + "', errori=" + getErrori() + "}";
  }
}
