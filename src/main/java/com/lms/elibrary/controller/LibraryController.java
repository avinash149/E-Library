package com.lms.elibrary.controller;

import com.lms.elibrary.dao.*;
import com.lms.elibrary.entity.*;
import com.lms.elibrary.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class LibraryController {

    @Autowired
    LibraryDao dao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    Validate check;

    @Autowired
    SendEmail email;

    @RequestMapping("issueBook")
    public ModelAndView issueBook(){
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "IssueBook");
            mav.addObject("s", new Student());
            mav.addObject("b", new Book());
        }
        return mav;
    }

    @RequestMapping("fetch")
    public ModelAndView fetch(@RequestParam("studID") int studID, @RequestParam("bookID") int bookID){
        Student student = new Student();
        Book book = new Book();
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        try {
            student = studentDao.findById(studID);
            book = bookDao.findById(bookID);

            if(!mav.getViewName().equals("index")){
                if(student!=null && book!=null){
                    mav.addObject("body", "IssueBook");
                    mav.addObject("s", student);
                    mav.addObject("b", book);
                }
                else if(student==null && book!=null){
                    mav.addObject("body", "Warning");
                    mav.addObject("content", "IssueBook");
                    mav.addObject("s", new Student());
                    mav.addObject("b", book);
                    mav.addObject("msg", "Student_id doesn't exist!");
                }
                else if(student!=null && book==null){
                    mav.addObject("body", "Warning");
                    mav.addObject("content", "IssueBook");
                    mav.addObject("s", student);
                    mav.addObject("b", new Book());
                    mav.addObject("msg", "Book_id doesn't exist!");
                }
                else {
                    mav.addObject("body", "Danger");
                    mav.addObject("content", "IssueBook");
                    mav.addObject("msg", "Student_id & Book_id doesn't exist!");
                    mav.addObject("s", new Student());
                    mav.addObject("b", new Book());
                }

            }
        }catch (Exception e){
            mav.addObject("body", "Danger");
            mav.addObject("content", "IssueBook");
            mav.addObject("msg", "Student_id or Book_id doesn't exist!");
            mav.addObject("s", new Student());
            mav.addObject("b", new Book());
        }
        return mav;
    }

    @RequestMapping("viewBookByStudName")
    public ModelAndView viewBookByStudName(@RequestParam("studid") int id) {
        //String studname = dao.findStudName(id);
        Student student = studentDao.findById(id);
        List<Library> list = null;
        if (id > 0)
            list = dao.findAllByStudidAndStatus(id, 'I');

        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index") && student != null && list.isEmpty()==false){
            mav.addObject("bookList", list);
            mav.addObject("body", "ReturnBook");
            mav.addObject("s", student);
        }
        else if (student == null){
            mav.addObject("body", "Warning");
            mav.addObject("content", "ReturnBook");
            mav.addObject("msg", "Student doesn't exist !");
            mav.addObject("bookList", list);
            mav.addObject("s", new Student());
        }
        else {
            mav.addObject("body", "Warning");
            mav.addObject("content", "ReturnBook");
            mav.addObject("msg", "No Book is issued !");
            mav.addObject("s", new Student());
            mav.addObject("bookList", null);
        }
        return mav;
    }

    @RequestMapping("returnBook")
    public ModelAndView returnBook(){

        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "ReturnBook");
            mav.addObject("bookList", null);
            mav.addObject("s", new Student());
        }
        return mav;
    }

    @RequestMapping("bookReturned")
    public ModelAndView bookReturned(@RequestParam("id") int id, @RequestParam("studid") int studid) throws MessagingException {
        Student student = studentDao.findById(studid);
        Library library = dao.findById(id);
        library.setStatus('R');
        dao.save(library);
        List<Library> lib = new ArrayList<Library>(Arrays.asList(library));
        List<Library> list = null;
        if(id>0)
            list = dao.findAllByStudidAndStatus(studid, 'I');

        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            if (!list.isEmpty()) {
                mav.addObject("s", student);
                mav.addObject("bookList", list);
                mav.addObject("body", "Danger");
                mav.addObject("content", "ReturnBook");
                mav.addObject("msg", "Book is successfully returned !");
            }
            else {
                mav.addObject("body", "Warning");
                mav.addObject("content", "ReturnBook");
                mav.addObject("msg", "No Book is issued !");
                mav.addObject("s", new Student());
                mav.addObject("bookList", null);
            }
        }
        email.sendEmail(lib, student,"Returned Book");
        return mav;
    }

    @RequestMapping("bookIssued")
    public ModelAndView bookIssued(Library lib) throws MessagingException {

        dao.save(lib);

        List<Library> list = dao.findAllByStudidAndStatusAndDate(lib.getStudid(),lib.getStatus(),lib.getDate());
        Student student = studentDao.findById(lib.getStudid());



        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "Success");
            mav.addObject("content", "IssueBook");
            mav.addObject("msg", "Book are successfully issued !");
            mav.addObject("s", new Student());
            mav.addObject("b", new Book());
        }

        email.sendEmail(list, student, "Book Issued");
        return mav;
    }
}
