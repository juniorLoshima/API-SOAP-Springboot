package com.ws.soap.webservices.soapcoursemanagement.soap;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.server.EndpointInterceptor;

import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


//Enable Spring Web Services
@EnableWs

//Spring Configuration
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	    //MessageDispatcherServlet
		//ApplicationContext
		//url -> /ws/*	
@Bean
public ServletRegistrationBean messageDispatcherServlet (ApplicationContext context) {
	MessageDispatcherServlet messageDispatcherServlet= new MessageDispatcherServlet();
	messageDispatcherServlet.setApplicationContext(context);
	messageDispatcherServlet.setTransformWsdlLocations(true);
	return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	
}

///ws/courses.wsdl
//PortType - CoursePort
//Namespace - https://www.formation.uvs.sn/courses
//course-details.xsd

@Bean (name="courses")
public DefaultWsdl11Definition defaultWsdl11Definition (XsdSchema coursesSchema) 
{
	DefaultWsdl11Definition definition = new DefaultWsdl11Definition ();
	// PortType - CoursePort
	definition.setPortTypeName("CoursePort");
	//Namespace - https://formation.uvs.sn/courses
	definition.setTargetNamespace("https://formation.uvs.sn/courses");
	// /ws
	definition.setLocationUri("/ws");
	//schema
	definition.setSchema(coursesSchema);
	
	return definition;
}

@Bean
public XsdSchema coursesSchema()
{
	return new SimpleXsdSchema (new ClassPathResource("course-details.xsd"));
	
}
@Bean
public XwsSecurityInterceptor  securityInterceptor() 
{
	XwsSecurityInterceptor securityInterceptor = new  XwsSecurityInterceptor();
	securityInterceptor.setCallbackHandler(callbackHandler());
	securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
	return securityInterceptor;
	
}

@Bean
public SimplePasswordValidationCallbackHandler callbackHandler() {
	SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
	handler.setUsersMap(Collections.singletonMap("user", "password"));
	return handler;
}

@Override
public void addInterceptors(List<EndpointInterceptor> interceptors) {
	
	interceptors.add(securityInterceptor());
}


}
 