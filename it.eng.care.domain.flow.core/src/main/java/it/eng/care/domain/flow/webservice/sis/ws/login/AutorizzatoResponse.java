package it.eng.care.domain.flow.webservice.sis.ws.login;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * WSDL:
 * <xs:element name="autorizzatoResponse" type="tns:autorizzatoResponse"/>
 * <xs:complexType name="autorizzatoResponse">
 *   <xs:sequence/>
 * </xs:complexType>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "autorizzatoResponse", namespace = "http://ws.sis.eng.it/")
public class AutorizzatoResponse {
    // response vuota nel WSDL
}