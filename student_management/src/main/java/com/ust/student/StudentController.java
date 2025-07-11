package com.ust.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ust.student.exceptions.StudentNotFoundException;
import com.ust.student.exceptions.EmailAlreadyExistingException;
import com.ust.student.exceptions.InvalidDepartmentException;
import com.ust.student.responses.ApiResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/v1/student")
public class StudentController {
	
	
	private final StudentService studentService;
	
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(){
		List<Student> studentsList = studentService.getAllStudents();
		ApiResponse<List<Student>> response = new ApiResponse<>("success", 200, "Employee fetched successfully", studentsList);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<Student>> addNewStudent(@RequestBody @Valid Student student) throws StudentNotFoundException, InvalidDepartmentException{
		Student newStudent = studentService.addNewStudent(student);
		ApiResponse<Student> response = new ApiResponse<>("created", 201, "Employee created successfully", newStudent);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{studentId}")
	public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long studentId) throws StudentNotFoundException {
		studentService.deleteStudent(studentId);
		ApiResponse<Void>  response = new ApiResponse<>("success",200, "Student deleted successfully", null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{studentId}")
	public ResponseEntity<ApiResponse<Student>> updateStudentById(@PathVariable Long studentId, @RequestBody Student student) throws StudentNotFoundException, EmailAlreadyExistingException {
		Student updateStudent = studentService.updateStudentById(studentId, student);
		ApiResponse<Student>  response = new ApiResponse<>("success",200, "Student added successfully", updateStudent);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
