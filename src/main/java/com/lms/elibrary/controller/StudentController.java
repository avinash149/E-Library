package com.lms.elibrary.controller;

import com.lms.elibrary.dao.StudentDao;
import com.lms.elibrary.entity.Student;
import com.lms.elibrary.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentDao dao;

    @Autowired
    HttpSession session;

    @Autowired
    Validate check;

    @RequestMapping("addStudent")
    public ModelAndView addStudent(){

        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "AddStudent");
        }

        return mav;
    }

    @RequestMapping("studentAdded")
    public ModelAndView studentAdded(Student student){

        dao.save(student);

        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "Success");
            mav.addObject("content", "AddStudent");
            mav.addObject("msg", "Student records are successfully saved !");
        }

        return mav;
    }

    @RequestMapping("viewStudent")
    public ModelAndView viewStudent(){
        List<Student> students = dao.findAll();
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            if (!students.isEmpty()){
                mav.addObject("students",students);
                mav.addObject("body", "ViewStudent");
            }
            else {
                mav.addObject("body", "Warning");
                mav.addObject("content", "ViewStudent");
                mav.addObject("msg", "Students are not available !");
            }
            mav.addObject("branch", "All");
        }
        return mav;
    }

    @RequestMapping("viewStudentById")
    public ModelAndView viewStudentById(@RequestParam("id") int id){
        Student students = dao.findById(id);
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            if (students!=null){
                mav.addObject("students",students);
                mav.addObject("body", "ViewStudent");
            }
            else {
                mav.addObject("body", "Warning");
                mav.addObject("content", "ViewStudent");
                mav.addObject("msg", "Students are not available !");
            }
            mav.addObject("branch", "All");
        }
        return mav;
    }

    @RequestMapping("viewStudentByBranch")
    public ModelAndView viewStudentByBranch(@RequestParam("branch") String branch){
        List<Student> students;
        if (branch.equals("All"))
            students = dao.findAll();
        else
            students = dao.findByBranch(branch);
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            if (!students.isEmpty()){
                mav.addObject("students",students);
                mav.addObject("body", "ViewStudent");
            }
            else {
                mav.addObject("body", "Warning");
                mav.addObject("content", "ViewStudent");
                mav.addObject("msg", "Students are not available !");
            }
            mav.addObject("branch", branch);
        }
        return mav;
    }

    @RequestMapping("editStudent")
    public ModelAndView editStudent(@RequestParam("id") int id){
        Student student = dao.findById(id);
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "UpdateStudent");
            mav.addObject("s", student);
        }
        return mav;
    }

    @RequestMapping("updateStudent")
    public ModelAndView updateBook(Student student){
        Student dbStudent = dao.findById(student.getId());
        if(dbStudent!=null){
            dbStudent.setName(student.getName());
            dbStudent.setBranch(student.getBranch());
            dbStudent.setSem(student.getSem());
            dbStudent.setMob(student.getMob());
            dbStudent.setEmail(student.getEmail());
            dbStudent.setAddress(student.getAddress());
        }
        dao.save(student);
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            mav.addObject("body", "Success");
            mav.addObject("content", "UpdateStudent");
            mav.addObject("s", new Student());
            mav.addObject("msg", "Student records are successfully updated !");
        }
        return mav;
    }

    @RequestMapping("deleteStudent")
    public ModelAndView deleteStudent(@RequestParam("id") int id){
        dao.deleteById(id);
        List<Student> students = dao.findAll();
        ModelAndView mav = new ModelAndView();
        mav = check.isUser(mav);
        if(!mav.getViewName().equals("index")){
            if (!students.isEmpty()) {
                mav.addObject("students", students);
                mav.addObject("body", "Danger");
                mav.addObject("content", "ViewStudent");
                mav.addObject("msg", "Student is successfully deleted !");
            }
            else {
                mav.addObject("body", "Warning");
                mav.addObject("content", "ViewStudent");
                mav.addObject("msg", "Students are not available !");
            }
        }
        return mav;
    }
}
