package it.eng.care.domain.flow.webservice.sis.ws.login;

import jakarta.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userDetail", propOrder = { "parameters" })
@XmlRootElement(name = "userDetail", namespace = "http://ws.sis.eng.it/")
public class UserDetail implements Serializable {

  @XmlElement(name = "parameters")
  private Parameters parameters;

  public Parameters getParameters() {
    if (parameters == null) parameters = new Parameters();
    return parameters;
  }

  public void setParameters(Parameters parameters) {
    this.parameters = parameters;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Parameters implements Serializable {

    @XmlElement(name = "entry")
    private List<Entry> entry;

    public List<Entry> getEntry() {
      if (entry == null) entry = new ArrayList<>();
      return entry;
    }

    public void setEntry(List<Entry> entry) {
      this.entry = entry;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Entry implements Serializable {

    @XmlElement(name = "key")
    private String key;

    @XmlElement(name = "value")
    private KeyValues value;

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public KeyValues getValue() { return value; }
    public void setValue(KeyValues value) { this.value = value; }
  }
}
