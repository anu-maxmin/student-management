package com.ust.student;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@Entity
public class Student {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="student_sequence")
	private Long studentId;
	
	@NotBlank(message = "Name is required")
	private String studentName;
	
	@NotNull(message = "Date of birth is required")
	@Past(message = "Invalid Date of Birth")
	private LocalDate studentDOB;
	
	@Transient
	private int age;
	@NotBlank(message = "Department is required")
	private String studentDepartment;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid Email")
	private String studentEmail;
	
	@Pattern(regexp = "^[6-9]\\d{9}$", message="Phone number should be between 10 digits")
	private String studentPhone;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String studentName, LocalDate studentDOB, String studentDepartment, String studentEmail,
			String studentPhone) {
		super();
		this.studentName = studentName;
		this.studentDOB = studentDOB;
		this.studentDepartment = studentDepartment;
		this.studentEmail = studentEmail;
		this.studentPhone = studentPhone;
	}

	public Student(Long studentId, String studentName, LocalDate studentDOB, String studentDepartment,
			String studentEmail, String studentPhone) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentDOB = studentDOB;
		this.studentDepartment = studentDepartment;
		this.studentEmail = studentEmail;
		this.studentPhone = studentPhone;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public LocalDate getStudentDOB() {
		return studentDOB;
	}

	public void setStudentDOB(LocalDate studentDOB) {
		this.studentDOB = studentDOB;
	}

	public int getAge() {
		return Period.between(this.studentDOB, LocalDate.now()).getYears();
	}

	public String getStudentDepartment() {
		return studentDepartment;
	}

	public void setStudentDepartment(String studentDepartment) {
		this.studentDepartment = studentDepartment;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

}
