package com.ejavashop.core.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {
    private String userName = null;
    private String password = null;

    public EmailAuthenticator() {
    }

    public EmailAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
