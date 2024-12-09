package Auction.AMS.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body) throws MailException {
        if (toEmail == null || toEmail.isEmpty()) {
            throw new IllegalArgumentException("Recipient email address cannot be null or empty.");
        }
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("hagi04201114@gmail.com"); // Replace with a valid email
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true); // true = HTML content
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailException("Failed to send email", e) {};
        }
    }
}
