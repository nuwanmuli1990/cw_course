package com.iit.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iit.course.models.AuthenticatioResponse;
import com.iit.course.models.AuthenticationRequest;
import com.iit.course.models.CourseResponse;
import com.iit.course.service.CourseService;
import com.iit.course.service.MyUserDetailsService;
import com.iit.course.util.JwtUtil;

@RestController
public class CourseController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CourseService courseService;
	
	@GetMapping(value = "/courses")
	@ResponseBody
	public List<CourseResponse> getStudents() {
		return courseService.getCourses();
	}
	
	@GetMapping(value = "/courses/{id}")
	@ResponseBody
	public CourseResponse getStudent(@PathVariable Long id) {
		return courseService.getCourse(id);
	}
	
	
	@RequestMapping(value ="/authenticate",method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
		authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}catch (BadCredentialsException e) {
			throw new Exception("Incorect username or pasword",e);
		}
		final UserDetails userDetails=myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt=jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticatioResponse(jwt));
	}
}
