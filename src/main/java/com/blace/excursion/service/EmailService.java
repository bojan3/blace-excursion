package com.blace.excursion.service;

import com.blace.excursion.emailContexts.EmailContext;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMail(final EmailContext email) throws MessagingException;

}
