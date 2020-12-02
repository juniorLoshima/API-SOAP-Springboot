package com.ws.soap.webservices.soapcoursemanagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{https://formation.uvs.sn/courses} COURS_NON_TROUVE ")
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4486488309303355103L;

	public CourseNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	


}
