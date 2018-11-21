package com.lms.elibrary.controller;

import com.lms.elibrary.dao.BookDao;
import com.lms.elibrary.dao.StudentDao;
import com.lms.elibrary.entity.Book;
import com.lms.elibrary.entity.Student;
import com.lms.elibrary.utility.Validate;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookDao dao;

    @Autowired
    Validate check;

    @RequestMapping("/addBook")
    public ModelAndView addBook() {
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if (!mav.getViewName().equals("index")) {
            mav.addObject("body", "AddBook");
            mav.addObject("b", new Book());
        }
        return mav;
    }

    @RequestMapping("bookAdded")
    public ModelAndView addBook(Book book){
        dao.save(book);
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "Success");
            mav.addObject("content", "AddBook");
            mav.addObject("msg", "Book records are successfully saved !");
        }
        return mav;
    }

    @RequestMapping("viewBook")
    public ModelAndView viewBook(){
        List<Book> bookList = dao.findAll();
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            if (!bookList.isEmpty()){
                mav.addObject("bookList",bookList);
                mav.addObject("body", "ViewBook");
            }
            else {
                mav.addObject("body", "Warning");
                mav.addObject("content", "ViewBook");
                mav.addObject("msg", "Books are not available !");
            }

        }
        return mav;
    }

    @RequestMapping("editBook")
    public ModelAndView editBook(@RequestParam("id") int id){
        Book book = dao.findById(id);
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "UpdateBook");
            mav.addObject("b", book);
        }
        return mav;
    }

    @RequestMapping("updateBook")
    public ModelAndView updateBook(Book book){
        Book dbBook = dao.findById(book.getId());
        if(dbBook!=null){
            dbBook.setAuthor(book.getAuthor());
            dbBook.setName(book.getName());
            dbBook.setQty(book.getQty());
        }
        dao.save(book);
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "Success");
            mav.addObject("content", "UpdateBook");
            mav.addObject("b", new Book());
            mav.addObject("msg", "Book records are successfully updated !");
        }
        return mav;
    }

    @RequestMapping("deleteBook")
    public ModelAndView deleteBook(@RequestParam("id") int id){
        dao.deleteById(id);
        List<Book> bookList = dao.findAll();
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            if (!bookList.isEmpty()) {
                mav.addObject("bookList", bookList);
                mav.addObject("body", "Danger");
                mav.addObject("content", "ViewBook");
                mav.addObject("msg", "Book is successfully deleted !");
            }
            else {
                mav.addObject("body", "Warning");
                mav.addObject("content", "ViewBook");
                mav.addObject("msg", "Books are not available !");
            }
        }
        return mav;
    }

}
