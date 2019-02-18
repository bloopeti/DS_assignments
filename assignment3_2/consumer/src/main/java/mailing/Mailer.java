package mailing;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class Mailer {
    final String username;
    final String password;
    final Properties props;

    public Mailer(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void sendSingleMail(String to, String subject, String content) {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Mail sent to '" + to + "'.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendMassMail(List<String> to, String subject, String content) {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setSubject(subject);
            message.setText(content);

            for (String client : to)
            {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(client));

                Transport.send(message);

                System.out.println("Mail sent to '" + client + "'.");
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
