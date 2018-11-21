package com.lms.elibrary.dao;

import com.lms.elibrary.entity.Book;
import com.lms.elibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
    Book findById(int id);
}
