package com.ws.soap.webservices.soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ws.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.ws.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.Status;

@Component
public class CourseDetailsService {

	public enum Status
	{
		SUCCES,ECHEC;
	}
	private static List<Course> courses = new ArrayList<>();
	
	static {
		
		Course course1 = new Course(1, "Ethique d'entreprise", "Autoformation");
		courses.add(course1);
		Course course2 = new Course (2, "CSI", "Conception Système Intelligent");
		courses.add(course2);
		Course course3 = new Course (3, "IHM", "Interface Homme Macine");
		courses.add(course3);
		Course course4 = new Course (4, "PO", "Projet Opérationnel");
		courses.add(course4);
		Course course5 = new Course (5, "SSI", "Sécurité de système d'information");
		courses.add(course5);
		}
	
	public Course findById(int id){
		for(Course course:courses)
		{
		if(course.getId()==id)
		return course;
		}
		return null;
	}
	
	public List<Course> findAll()
	{
		return courses;
	}
	
	public Status deleteById(int id)
	{
	Iterator<Course> iterator = courses.iterator();
	while (iterator.hasNext()){
		Course course = iterator.next();
		if(course.getId()==id)
		{
		iterator.remove();
		return Status.SUCCES;
		}
		
	}
	return Status.ECHEC;
	}
}
