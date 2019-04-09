package com.rumango.median;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedianIsoApplication {

	public static void main(String[] args) {
	//	System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(MedianIsoApplication.class, args);
	}
	
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//	  TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//	      @Override
//	      protected void postProcessContext(Context context) {
//	        SecurityConstraint securityConstraint = new SecurityConstraint();
//	        securityConstraint.setUserConstraint("CONFIDENTIAL");
//	        SecurityCollection collection = new SecurityCollection();
//	        collection.addPattern("/*");
//	        securityConstraint.addCollection(collection);
//	        context.addConstraint(securityConstraint);
//	      }
//	    };
//	   
//	  tomcat.addAdditionalTomcatConnectors(redirectConnector());
//	  return tomcat;
//	}
//	 
//	private Connector redirectConnector() {
//	  Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//	  connector.setScheme("http");
//	  connector.setPort(8080);
//	  connector.setSecure(false);
//	  connector.setRedirectPort(8443);
//	   
//	  return connector;
//	}
}
