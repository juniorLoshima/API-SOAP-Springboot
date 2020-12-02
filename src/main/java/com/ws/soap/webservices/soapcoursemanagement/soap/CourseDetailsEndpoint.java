package com.ws.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ws.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.ws.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.ws.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.Status;

import https.formation_uvs_sn.courses.CourseDetails;
import https.formation_uvs_sn.courses.DeleteCourseDetailsRequest;
import https.formation_uvs_sn.courses.DeleteCourseDetailsResponse;
import https.formation_uvs_sn.courses.GetAllCourseDetailsRequest;
import https.formation_uvs_sn.courses.GetAllCourseDetailsResponse;
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
		Course course = service.findById(request.getId());
	
		return mapCourseDetails(course);
		
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}
	
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for(Course course:courses){
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
			}
			return response;
	}
	
	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId (course.getId());
		courseDetails.setName (course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	@PayloadRoot(namespace="https://formation.uvs.sn/courses", localPart = "GetAllCourseDetailsRequest" )
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) 
	{
		List<Course> courses = service.findAll();
	
		return mapAllCourseDetails(courses);
		
	}
		
	@PayloadRoot(namespace = "https://formation.uvs.sn/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload 
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) 
	{
		Status status = service.deleteById(request.getId());
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
		
	}

	private https.formation_uvs_sn.courses.Status mapStatus(Status status) {
		
		if(status==Status.ECHEC)
			
			return https.formation_uvs_sn.courses.Status.ECHEC;
		return https.formation_uvs_sn.courses.Status.SUCCES;
	}

}
