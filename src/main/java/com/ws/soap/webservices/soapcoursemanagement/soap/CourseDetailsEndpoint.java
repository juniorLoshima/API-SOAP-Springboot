package com.ws.soap.webservices.soapcoursemanagement.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ws.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.ws.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;

import https.formation_uvs_sn.courses.CourseDetails;
import https.formation_uvs_sn.courses.GetCourseDetailsRequest;
import https.formation_uvs_sn.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
	
	   @Autowired
	   CourseDetailsService service;
	   
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
		
		Course course = service.findById(request.getId());
		
		return mapCourse(response, course); 
		
	}

		private GetCourseDetailsResponse mapCourse(GetCourseDetailsResponse response, Course course) {
			CourseDetails courseDetails = new CourseDetails();
			courseDetails.setId (course.getId());
			courseDetails.setName (course.getName());
			courseDetails.setDescription(course.getDescription());
			response.setCourseDetails(courseDetails);
			return response;
		}

}
