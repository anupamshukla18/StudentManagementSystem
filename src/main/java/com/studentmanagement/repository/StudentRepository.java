package com.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // Custom query method to search students by name
    List<Student> findByNameContainingIgnoreCase(String name);
}