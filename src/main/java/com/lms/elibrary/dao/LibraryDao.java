package com.lms.elibrary.dao;
import com.lms.elibrary.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface LibraryDao extends JpaRepository<Library, Integer> {

    List<Library> findAllByStudidAndStatusAndDate(int studID, char status, String date);
    List<Library> findAllByStudidAndStatus(int studID, char status);
    List<Library> findAllByStatus(char status);
    List<Library> findByStudid(int id);
    Library findById(int id);


    /*@Query(value = "select studname from libmaster where  studid = ?1 and status = 'I'", nativeQuery = true)
    String findStudName(int id);*/
}
