package it.eng.care.domain.flow.webservice.sis.ws.login;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

/**
 * WSDL:
 * <xs:complexType name="keyValues">
 *   <xs:sequence>
 *     <xs:element maxOccurs="unbounded" minOccurs="0" name="values" nillable="true" type="xs:string"/>
 *   </xs:sequence>
 * </xs:complexType>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class KeyValues {

    @XmlElement(name = "values", nillable = true)
    private List<String> values;

    public List<String> getValues() {
        if (values == null) values = new ArrayList<>();
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}