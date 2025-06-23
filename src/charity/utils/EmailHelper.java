package charity.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailHelper {
    public static boolean send(String to, String subject, String content) {
        final String username = "your_gmail@gmail.com"; // Thay bằng email gửi
        final String password = "your_app_password";    // Thay bằng app password (không phải mật khẩu Gmail thường)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "UTC2 Charity"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}