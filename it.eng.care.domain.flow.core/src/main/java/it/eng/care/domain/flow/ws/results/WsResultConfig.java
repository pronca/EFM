package it.eng.care.domain.flow.ws.results;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WsResultConfig extends WsConfigurerAdapter {
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
	    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	    servlet.setApplicationContext(applicationContext);
	    servlet.setTransformWsdlLocations(true);
	    return new ServletRegistrationBean(servlet, "/fm/ws/results/sdo/*");
	}
	
	@Bean(name = "wsresults")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema wsResultsSchema) {
	    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    wsdl11Definition.setPortTypeName("WsResultsPort");
	    wsdl11Definition.setLocationUri("/ws/results");
	    wsdl11Definition.setTargetNamespace("http://www.eng.it/fm/ws/results/sdo");
	    wsdl11Definition.setSchema(wsResultsSchema);
	    return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema wsResultsSchema() {
	    return new SimpleXsdSchema(
	    		new ClassPathResource("sdoerrors.xsd"));
	}
	
	@Bean
	public WsResultEndpoint WsResultEndpoint() {
		return new WsResultEndpoint();
	}

}
