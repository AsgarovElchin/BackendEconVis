package com.example.demo.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.io.IOException;

public class EmailService {
    private static final String SENDGRID_API_KEY = "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";

    public static void sendPasswordResetEmail(String toEmail, String resetCode) throws IOException {
        Email from = new Email("econvisauth@gmail.com", "Econvis Auth");
        String subject = "Password Reset Request";
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", "To reset your password, use the following code: " + resetCode);
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject(subject);

        Personalization personalization = new Personalization();
        personalization.addTo(to);
        mail.addPersonalization(personalization);

        mail.addContent(content);

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}