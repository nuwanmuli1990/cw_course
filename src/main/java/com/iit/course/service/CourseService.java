package com.iit.course.service;

import java.util.List;

import com.iit.course.models.CourseResponse;

public interface CourseService {

	List<CourseResponse> getCourses();

	CourseResponse getCourse(Long id);
}
