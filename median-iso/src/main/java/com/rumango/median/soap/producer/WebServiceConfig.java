/**
 * 
 */
package com.rumango.median.soap.producer;

//import org.apache.log4j.Logger;

/**
 * @author lei2o
 *
 */

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter
{
	// private static final Logger LOGGER =
	// Logger.getLogger(WebServiceConfig.class);

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
			ApplicationContext applicationContext)
	{
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}

	@Bean(name = "ftservice")
	public DefaultWsdl11Definition defaultWsdl11Definition()
	{
		// LOGGER.info("WebServiceConfig,DefaultWsdl11Definition ======>");
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("FtServicePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://fcubs.ofss.com/service/FCUBSFTService");
		wsdl11Definition.setSchemaCollection(ftServicesSchemas());
		return wsdl11Definition;
	}

	@Bean
	public XsdSchemaCollection ftServicesSchemas()
	{
		// LOGGER.info("=======>WebServiceConfig,ftServicesSchemas<======");
		CommonsXsdSchemaCollection xsds = new CommonsXsdSchemaCollection(
				new ClassPathResource("FLEXCUBE/FT_SERVICE/ftservice.xsd"));
		xsds.setInline(true);
		return xsds;
	}
}