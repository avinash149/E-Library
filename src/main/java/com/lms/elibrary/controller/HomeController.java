package com.lms.elibrary.controller;

import com.lms.elibrary.dao.AdminDao;
import com.lms.elibrary.dao.UserDao;
import com.lms.elibrary.entity.Admin;
import com.lms.elibrary.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    AdminDao adminDao;

    @Autowired
    UserDao userDao;

    @Autowired
    HttpSession session;

    @RequestMapping(value = {"/", "/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        session.invalidate();
        return "index";
    }

    @RequestMapping("/home")
    public ModelAndView home(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        ModelAndView mav = new ModelAndView("login");
        if(username.equalsIgnoreCase("admin"))
        {
            Admin user = adminDao.findByUsernameAndPassword(username, password);
            if(user != null)
            {
                mav.addObject("body", "AddStudent");
                mav.setViewName("adminTemplate");
                session.setAttribute("user", username);
            }
        }
        else if(username!="admin")
        {
            User user = userDao.findByEmailAndPassword(username, password);
            if(user != null)
            {
                mav.addObject("body", "AddStudent");
                mav.setViewName("userTemplate");
                session.setAttribute("user", user.getName());
                System.out.println(user.getName());
            }
        }

        return mav;
    }
}
