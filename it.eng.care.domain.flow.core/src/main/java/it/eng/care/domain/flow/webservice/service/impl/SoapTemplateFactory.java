package it.eng.care.domain.flow.webservice.service.impl;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import it.eng.care.domain.flow.core.utility.LogUtil;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.soap.SOAPException;

public final class SoapTemplateFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SoapTemplateFactory.class);
	
    private SoapTemplateFactory() {}

    // Cache marshaller per set di classi (evita init JAXB ad ogni chiamata)
    private static final ConcurrentHashMap<String, Jaxb2Marshaller> MARSHALLER_CACHE = new ConcurrentHashMap<>();

    /**
     * Crea un WebServiceTemplate configurato (SOAP 1.1) usando le classi JAXB annotate.
     *
     * @param connectTimeoutMs timeout connessione
     * @param readTimeoutMs timeout lettura
     * @param boundClasses tutte le classi JAXB coinvolte (Request/Response + eventuali tipi annidati)
     */
    public static WebServiceTemplate create(
            int connectTimeoutMs,
            int readTimeoutMs,
            Class<?>... boundClasses
    ) {
        Objects.requireNonNull(boundClasses, "boundClasses is null");
        if (boundClasses.length == 0) {
            throw new IllegalArgumentException("boundClasses is empty: serve almeno una classe JAXB");
        }

        // 1) JAXB marshaller (da classi annotate, NON da contextPath)
        String key = marshallerKey(boundClasses);
        Jaxb2Marshaller marshaller = MARSHALLER_CACHE.computeIfAbsent(key, k -> buildMarshaller(boundClasses));

        // 2) HTTP sender + timeout
        HttpComponentsMessageSender sender = new HttpComponentsMessageSender();
        sender.setConnectionTimeout(connectTimeoutMs);
        sender.setReadTimeout(readTimeoutMs);

        // 3) SOAP 1.1
        SaajSoapMessageFactory msgFactory = new SaajSoapMessageFactory(buildSoap11MessageFactory());
        msgFactory.afterPropertiesSet();

        // 4) WebServiceTemplate
        WebServiceTemplate template = new WebServiceTemplate(msgFactory);
        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        template.setMessageSender(sender);

        return template;
    }

    private static Jaxb2Marshaller buildMarshaller(Class<?>... boundClasses) {
        try {
            Jaxb2Marshaller m = new Jaxb2Marshaller();
            m.setClassesToBeBound(boundClasses);
            m.afterPropertiesSet();
            return m;
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "Errore init JAXB (classesToBeBound)", e);
        	
            throw new IllegalStateException("Errore init JAXB (classesToBeBound)", e);
        }
    }

    private static MessageFactory buildSoap11MessageFactory() {
        try {
            return MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        } catch (SOAPException e) {
        	LogUtil.logException(LOGGER, "Errore init SOAP MessageFactory (SOAP 1.1)", e);
        	
            throw new IllegalStateException("Errore init SOAP MessageFactory (SOAP 1.1)", e);
        }
    }

    private static String marshallerKey(Class<?>... boundClasses) {
        // key stabile: nomi classi ordinati
        return Arrays.stream(boundClasses)
                .filter(Objects::nonNull)
                .map(Class::getName)
                .sorted()
                .reduce((a, b) -> a + "|" + b)
                .orElseThrow();
    }
}