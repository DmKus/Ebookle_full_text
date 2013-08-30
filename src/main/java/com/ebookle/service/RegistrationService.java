package com.ebookle.service;

import com.ebookle.entity.User;
import com.ebookle.util.RandomKeyCreator;
import com.ebookle.util.UtilStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 25.08.13
 * Time: 5:51
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Service
public class RegistrationService {

    @Autowired
    private SimpleMailMessage preConfiguredMessage;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserService userService;

    public void register(String login, String password, String email, String name, String surname) {

        String userKey = RandomKeyCreator.createRandomKey();
        User user = new User(
                login,
                password,
                email,
                name,
                surname,
                userKey,
                UtilStrings.USER_ROLE_TEXT,
                false
        );
        userService.saveOrUpdate(user);
        sendKeyToMail(email,
                "Registration to ebookle.com",
                "http://localhost:8080/registration_success?key=" + userKey
        );
    }

    private void sendKeyToMail (String to, String topic, String message) {
        preConfiguredMessage.setTo(to);
        preConfiguredMessage.setSubject(topic);
        preConfiguredMessage.setText(message);
        mailSender.send(preConfiguredMessage);
    }

    public boolean activateUser(String key) {
        List<User> users = userService.findAllNotActivatedByKey(key);
        if (users.size() != 1) {
            return false;
        }
        User user = users.get(0);
        user.setActivated(true);
        userService.saveOrUpdate(user);
        return true;
    }
}
