package com.lms.elibrary.dao;

import com.lms.elibrary.entity.Student;
import com.lms.elibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Integer> {
    Student findById(int id);
    List<Student> findByBranch(String branch);


}
