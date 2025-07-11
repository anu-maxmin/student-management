package com.ust.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ust.student.exceptions.EmailAlreadyExistingException;
import com.ust.student.exceptions.InvalidDepartmentException;
import com.ust.student.exceptions.StudentNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	List<String> department = List.of("CSE","ECE", "IT");

	@Autowired
	private StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@PostMapping
	public Student addNewStudent(Student student) throws StudentNotFoundException, InvalidDepartmentException {
		Optional<Student> studentEmailOptional = studentRepository.findStudentByStudentEmail(student.getStudentEmail());
		if (studentEmailOptional.isPresent()) {
			throw new StudentNotFoundException("Email Id already exist....");
		}
		
		if(!department.contains(student.getStudentDepartment())) {
			throw new InvalidDepartmentException("Department is invalid");
		}

		return studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) throws StudentNotFoundException {
		boolean isExisting = studentRepository.existsById(studentId);
		if (!isExisting) {
			throw new StudentNotFoundException("Student id doesn't exist");
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public Student updateStudentById(Long studentId, Student student)
			throws StudentNotFoundException, EmailAlreadyExistingException {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		
		if(studentOptional.isEmpty()) {
			throw new StudentNotFoundException("Student id doesn't exist");
		}
		Student existingStudent = studentOptional.get(); 

		if ( student.getStudentName() != null && student.getStudentName().length() > 0 && !Objects.equals(existingStudent.getStudentName(), student.getStudentName())) {
			existingStudent.setStudentName(null);
		}
		
		if (student.getStudentDepartment() != null && student.getStudentDepartment().length() > 0 && !Objects.equals(existingStudent.getStudentDepartment(), student.getStudentDepartment())) {
			existingStudent.setStudentDepartment(student.getStudentDepartment());
		}
		
		if (student.getStudentPhone() != null && student.getStudentPhone().length() > 0 && !Objects.equals(existingStudent.getStudentPhone(), student.getStudentPhone())) {
			existingStudent.setStudentPhone(student.getStudentPhone());
		}
		
		if (student.getStudentEmail() != null && student.getStudentEmail().length() > 0 && !Objects.equals(existingStudent.getStudentEmail(), student.getStudentEmail())) {

			Optional<Student> existingNewStudent = studentRepository.findStudentByStudentEmail(student.getStudentEmail());
			if (existingNewStudent.isPresent()) {
				throw new EmailAlreadyExistingException("Email Id already exist");
			}
			existingStudent.setStudentEmail(student.getStudentEmail());
		}
		return studentRepository.save(existingStudent);
	}

}
