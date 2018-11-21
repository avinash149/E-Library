package com.lms.elibrary.dao;

import com.lms.elibrary.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, Integer> {
    Admin findByUsernameAndPassword(String var1, String var2);
}
