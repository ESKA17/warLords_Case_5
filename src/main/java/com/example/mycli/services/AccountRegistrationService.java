package com.example.mycli.services;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AccountRegistrationService {

    void registerAccount(String email, String password, String fullName, int role);
    void twoStepVerificationEmail(String email, String siteURL)
            throws MessagingException, UnsupportedEncodingException;

    boolean verifyUser(String code);

}
