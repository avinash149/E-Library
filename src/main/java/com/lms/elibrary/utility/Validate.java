package com.lms.elibrary.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Component
public class Validate {

    @Autowired
    HttpSession session;

    public ModelAndView isUser(ModelAndView mav)
    {
        String template="";
        if(session.getAttribute("user")!= null)
            template = session.getAttribute("user").toString();

        if (template.equalsIgnoreCase("admin")){
            mav.setViewName("adminTemplate");
        }

        else if (template!=""){
            mav.setViewName("userTemplate");
        }

        else {
            mav.clear();
            mav.setViewName("index");
        }

        return mav;
    }

    public ModelAndView isAdmin(ModelAndView mav)
    {
        /*String template="";
        if(session.getAttribute("user")!= null)
            template = session.getAttribute("user").toString();

        if (template.equalsIgnoreCase("admin")){
            mav.setViewName("adminTemplate");
        }
        else {
            mav.clear();
            mav.setViewName("index");
        }*/

        mav.setViewName("adminTemplate");
        return mav;
    }
}
