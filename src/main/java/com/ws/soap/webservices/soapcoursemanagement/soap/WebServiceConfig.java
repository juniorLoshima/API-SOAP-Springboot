package com.ws.soap.webservices.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;


//Enable Spring Web Services
@EnableWs

//Spring Configuration
@Configuration
public class WebServiceConfig {
	    //MessageDispatcherServlet
		//ApplicationContext
		//url -> /ws/*	
@Bean
ServletRegistrationBean messageDispatcherServlet (ApplicationContext context) {
	MessageDispatcherServlet messageDispatcherServlet= new MessageDispatcherServlet();
	messageDispatcherServlet.setApplicationContext(context);
	messageDispatcherServlet.setTransformWsdlLocations(true);
	return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	
}
}
 