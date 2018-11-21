package com.lms.elibrary.controller;

import com.lms.elibrary.dao.AdminDao;
import com.lms.elibrary.dao.UserDao;
import com.lms.elibrary.entity.Admin;
import com.lms.elibrary.entity.User;
import com.lms.elibrary.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserDao dao;

    @Autowired
    HttpSession session;

    @Autowired
    Validate check;

    @RequestMapping("addUser")
    public ModelAndView addUser()
    {
        ModelAndView mav = new ModelAndView();
        mav = check.isAdmin(mav);
        if (mav.getViewName().equals("adminTemplate"))
        {
            mav.addObject("body", "AddStaff");
        }

        return mav;
    }

    @RequestMapping("userAdded")
    public ModelAndView staffAdded(User user){

        dao.save(user);

        ModelAndView mav = new ModelAndView();
        mav = check.isAdmin(mav);
        if (mav.getViewName().equals("adminTemplate")) {
            mav.addObject("body", "Success");
            mav.addObject("content", "AddStaff");
            mav.addObject("msg", "Staff records are successfully saved !");
        }
        return mav;
    }

    @RequestMapping("editUser")
    public ModelAndView editUser(@RequestParam("id") int id){
        User user = dao.findById(id);

        ModelAndView mav = new ModelAndView();
        mav = check.isAdmin(mav);
        if (mav.getViewName().equals("adminTemplate")) {
            mav.addObject("body", "UpdateStaff");
            mav.addObject("u", user);
        }
        return mav;
    }

    @RequestMapping("updateUser")
    public ModelAndView updateUser(User user){
        User dbUser = dao.findById(user.getId());
        if(dbUser!=null){
            dbUser.setName(user.getName());
            dbUser.setAdhar(user.getAdhar());
            dbUser.setMob(user.getMob());
            dbUser.setEmail(user.getEmail());
            dbUser.setPassword(user.getPassword());
            dbUser.setAddress(user.getAddress());
        }
        dao.save(dbUser);

        ModelAndView mav = new ModelAndView();
        mav = check.isAdmin(mav);
        if (mav.getViewName().equals("adminTemplate")) {
            mav.addObject("body", "Success");
            mav.addObject("content", "UpdateStaff");
            mav.addObject("u", new User());
            mav.addObject("msg", "Staff records are successfully updated !");
        }
        return mav;
    }

    @RequestMapping("deleteUser")
    public ModelAndView deleteUser(@RequestParam("id") int id){
        dao.deleteById(id);
        List<User> users = dao.findAll();

        ModelAndView mav = new ModelAndView();
        mav = check.isAdmin(mav);
        if (mav.getViewName().equals("adminTemplate")) {
            if (!users.isEmpty()) {
                mav.addObject("users", users);
                mav.addObject("body", "Danger");
                mav.addObject("content", "ViewStaff");
                mav.addObject("msg", "Staff is successfully deleted !");
            }
            else {
                mav.addObject("body", "Warning");
                mav.addObject("content", "ViewStaff");
                mav.addObject("msg", "Staffs are not available !");
            }
        }
        return mav;
    }

    @RequestMapping("viewUser")
    public ModelAndView viewUser(){
        List<User> users = dao.findAll();
        ModelAndView mav = new ModelAndView();
        mav = check.isAdmin(mav);
        if (mav.getViewName().equals("adminTemplate")) {
            if (!users.isEmpty()) {
                mav.addObject("users", users);
                mav.addObject("body", "ViewStaff");
            } else {
                mav.addObject("body", "Warning");
                mav.addObject("content", "ViewStaff");
                mav.addObject("msg", "Staffs are not available !");
            }
        }
        return mav;
    }
}
