package com.example.demo.Service;

import com.example.aouth.Entity.UserOAUTH2;
import com.example.aouth.Repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;


    public UserOAUTH2 getUser(AbstractAuthenticationToken token) throws IllegalAccessException {
        Map<String , Object> params ;
        if (token instanceof OAuth2AuthenticationToken) {
            params = ((OAuth2AuthenticationToken)token).getPrincipal().getAttributes();
        }else
            throw new IllegalAccessException("ERROR");
        sendMail((String) params.get("email"),"bu email royxatdan otkazildi");
        return oauth2(params);
    }

    private UserOAUTH2 oauth2(Map<String, Object> params) {
        UserOAUTH2 user = new UserOAUTH2();
        if (params.get("given_name") != null) {
            user.setName(params.get("given_name").toString());
        }
        if (params.get("family_name") != null) {
            user.setLastname(params.get("family_name").toString());
        }
        if (params.get("email") != null) {
            user.setEmail(params.get("email").toString());
        }
        if (params.get("email_verified") != null) {
            user.setActive((Boolean) params.get("email_verified"));
        }
        if (params.get("picture") != null) {
            user.setImageUrl(params.get("picture").toString());
        }
        UserOAUTH2 save = userRepository.save(user);
        return save;

    }


    public boolean sendMail(String to, String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject("you are verified by Shukurjon ");
            helper.setText(text);
            helper.setFrom("shukurjonboqiyev0@gmail.com");
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException | MailException e) {
            return false;
        }
    }
}
