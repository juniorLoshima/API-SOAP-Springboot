package com.ws.soap.webservices.soapcoursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.formation_uvs_sn.courses.CourseDetails;
import https.formation_uvs_sn.courses.GetCourseDetailsRequest;
import https.formation_uvs_sn.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
	
	    //method
		//input - GetCourseDetailsRequest
		//output - GetCourseDetailsResponse
		
		//https://formation.uvs.sn/courses
		//GetCourseDetailsRequest
	@PayloadRoot(namespace="https://formation.uvs.sn/courses", localPart = "GetCourseDetailsRequest" )
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) 
	{
		
		GetCourseDetailsResponse response = new GetCourseDetailsResponse(); 
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId (request.getId());
		courseDetails.setName ("SOA");
		courseDetails.setDescription("Architecture Orientée Service");
		response.setCourseDetails(courseDetails);
		return response; 
		
	}

}
