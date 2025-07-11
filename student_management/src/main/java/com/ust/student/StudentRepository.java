package com.ust.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student, Long>{
	@Query("SELECT stu FROM Student stu WHERE stu.studentEmail = ?1 ")
	Optional<Student> findStudentByStudentEmail(String studentEmail);
	
}
