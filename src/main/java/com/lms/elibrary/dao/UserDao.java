package com.lms.elibrary.dao;

import com.lms.elibrary.entity.Admin;
import com.lms.elibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String var1, String var2);
    User findById(int id);
}
