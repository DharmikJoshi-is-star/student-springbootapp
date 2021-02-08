package com.student.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.exception.StudentNotFoundException;
import com.student.model.Student;
import com.student.service.StudentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/{sId}")
	@ApiOperation(value = "Get Student details with valid student id",
					notes="Provide an id to look up specific student details")
	public ResponseEntity<Student> getStudent(@PathVariable("sId") Long sId) {
		try {
			Student student = studentService.getStudent(sId);
			return ResponseEntity.ok().body(student);
		}catch(StudentNotFoundException studentNotFoundException) {
			return ResponseEntity.ok().body(null);
		}
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Save Student details",
					notes="Provide a name of student do not provide id it will get auto generated")
	public Student saveStudent(@RequestBody Student student) {
		student.toString();
		return studentService.saveStudent(student);
	}
	
	@PutMapping("/{sId}")
	@ApiOperation(value = "Update Student details with updated details and student id",
					notes="Provide a valid student id and student details")
	public ResponseEntity<Student> updateStudent(@PathVariable("sId") Long sId, @RequestBody Student studentDetails) {
		try {
			Student student = studentService.updateStudent(sId, studentDetails);
			return ResponseEntity.ok().body(student);
		}catch(StudentNotFoundException studentNotFoundException) {
			return ResponseEntity.ok().body(null);
		}
	}
	
	@DeleteMapping("/{sId}")
	@ApiOperation(value = "Delete Student details from databse with valid student id",
					notes="Provide an id to delete specific student details")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable("sId") Long sId) {
		Map<String, Boolean>response = new HashMap<>();
		try{
			response.put("value Deleted", studentService.deleteStudent(sId));
		}catch(StudentNotFoundException studentNotFoundException) {
			response.put("value Deleted", false);
		}
		return ResponseEntity.ok().body(response); 	
	}
	
}
