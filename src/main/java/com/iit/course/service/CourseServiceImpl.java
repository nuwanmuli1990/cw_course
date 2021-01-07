package com.iit.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.course.entities.Course;
import com.iit.course.models.CourseResponse;
import com.iit.course.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;

	
	@Override
	public List<CourseResponse> getCourses() {
		
		List<Course> courses = courseRepository.findAll();

		List<CourseResponse> response = new ArrayList<>();
		for (Course course : courses) {

			CourseResponse courseResponse = new CourseResponse(course.getId(), course.getName(),
					course.getCourseCredit(),course.getDuration());
			response.add(courseResponse);
		}
		return response;
	}

	@Override
	public CourseResponse getCourse(Long id) {

		CourseResponse response = new CourseResponse();
		Optional<Course> optionalCourse = courseRepository.findById(id);
		if(optionalCourse.isPresent()) {
			
			response.setId(optionalCourse.get().getId());
			response.setName(optionalCourse.get().getName());
			response.setCourseCredit(optionalCourse.get().getCourseCredit());
			response.setDuration(optionalCourse.get().getDuration());
		}
		return response;
	}

}
