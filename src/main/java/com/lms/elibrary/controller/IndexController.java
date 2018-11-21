package com.lms.elibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {



    @RequestMapping("user")
    public ModelAndView user(){
        ModelAndView mav = new ModelAndView("template");
        //mav.addObject("title", "Java Spring Project");
        mav.addObject("body", "user");
        return mav;
    }

    @RequestMapping("signup")
    public ModelAndView signup(){
        ModelAndView mav = new ModelAndView("template");
        mav.addObject("body", "SignUp");
        return mav;
    }
    @RequestMapping("studentReport")
    public ModelAndView studentReport(){
        ModelAndView mav = new ModelAndView("adminTemplate");
        mav.addObject("studList",null);
        mav.addObject("body", "StudentReport");
        return mav;
    }

    @RequestMapping("bookReport")
    public ModelAndView bookReport(){
        ModelAndView mav = new ModelAndView("adminTemplate");
        mav.addObject("bookList",null);
        mav.addObject("body", "BookReport");
        return mav;
    }

    @RequestMapping("profile")
    public ModelAndView profile(){
        ModelAndView mav = new ModelAndView("adminTemplate");
        mav.addObject("staff",null);
        mav.addObject("body", "Profile");
        return mav;
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome(){
        ModelAndView mav = new ModelAndView("adminTemplate");
        mav.addObject("body", "AddStudent");
        return mav;
    }
}
