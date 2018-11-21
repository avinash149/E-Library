package com.lms.elibrary.utility;
import com.lms.elibrary.entity.Library;
import com.lms.elibrary.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class SendEmail {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SpringTemplateEngine templateEngine;

    public void sendEmail(List<Library> list, Student student, String subject) throws MessagingException {

        //SimpleMailMessage mail = new SimpleMailMessage();
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("lib", list);
        context.setVariable("studname", student.getName());
        String html = templateEngine.process("Notification", context);
        mail.setTo(student.getEmail());
        mail.setFrom("noreply.elibrary.system@gmail.com");
        mail.setSubject(subject);
        mail.setText(html, true);

        javaMailSender.send(msg);
    }
}
