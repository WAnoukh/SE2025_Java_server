package com.example.aidsystem.service;
/* 
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {

    private static final String SMTP_SERVER = "smtp.example.com"; // SMTP сервер
    private static final String USERNAME = "your-email@example.com"; // Ваш email
    private static final String PASSWORD = "your-password"; // Ваш пароль
    private static final String FROM_EMAIL = "your-email@example.com"; // Адрес отправителя

    public void sendEmail(String toEmail, String subject, String body) {
        // Устанавливаем свойства для SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_SERVER);
        properties.put("mail.smtp.port", "587"); // Обычно используется порт 587 для отправки по SMTP
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Создаем сессию
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Создаем сообщение
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            // Отправляем письмо
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
*/ 