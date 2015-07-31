package util.misc;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;
import models.User;
import play.Logger;
import util.misc.Env.Variable;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by peterrangelov on 7/29/15.
 */
public class Mailer {
    public static boolean sendActivationToken (String emailAddress, String token) {
        SendGrid sendgrid = new SendGrid(Env.get(Variable.SENDGRID_USERNAME), Env.get(Variable.SENDGRID_PASSWORD));
        // FIND USER
//        String emailAddress = User.findByEmail("peter.rangelov11@gmail.com").emailAddress;


        Email email = new Email();
        email.addTo(emailAddress);
        email.setFrom("peter.rangelov11@gmail.com");
        email.setSubject("Activate your Jobmap account");
        email.setHtml(String.format("<a href='%sactivate/%s'>Click here</a> to activate your account.", Env.get(Variable.HOST), token));

        try {
            SendGrid.Response response = sendgrid.send(email);
            System.out.println(response.getMessage());
            return response.getStatus();
        }
        catch (SendGridException e) {
            System.err.println(e);
            return false;
        }
    }

    public static boolean sendActivationConfirmation (String emailAddress) {
        SendGrid sendgrid = new SendGrid(Env.get(Variable.SENDGRID_USERNAME), Env.get(Variable.SENDGRID_PASSWORD));
        Email email = new Email();
        email.addTo(emailAddress);
        email.setFrom("peter.rangelov11@gmail.com");
        email.setSubject("Your Jobmap account has been activated");
        email.setText("Account activated.");

        try {
            SendGrid.Response response = sendgrid.send(email);
            System.out.println(response.getMessage());
            return true;
        }
        catch (SendGridException e) {
            System.err.println(e);
            return false;
        }
    }


//    public static void sendActivationToken (String token) {
//        Email email = new SimpleEmail();
//        email.setHostName("smtp.googlemail.com");
//        email.setSmtpPort(465);
//        email.setSSLOnConnect(true);
//        email.setStartTLSRequired(true);
//        email.setAuthenticator(new DefaultAuthenticator("peter.rangelov11@gmail.com", "QzWxEcRv1!2@3#"));
//
//
//        try {
//            email.setFrom("peter.rangelov11@gmail.com");
//            email.setSubject("TestMail");
//            email.setMsg("This is a test mail ... :-)");
//            email.addTo("peter.rangelov11@gmail.com");
//            email.send();
//        }
//        catch (EmailException e) {
//            e.printStackTrace();
//        }
//    }


    public static Properties props = new Properties();



//    public static void send(String filepath) {
//        configureProperties();
//
//        Session session = Session.getInstance(props, new Authenticator(){
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
//            }
//        });
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject("efts.sub");
//
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setHeader("Content-Transfer-Encoding", "quoted-printable");
////            messageBodyPart.setText("sometext=3Dothertext");
//            messageBodyPart.setText("");
//
//
//            // ATTACHMENT
//            MimeBodyPart attachmentPart = new MimeBodyPart();
//            attachmentPart.setDisposition(MimeBodyPart.INLINE);
//            DataSource source = new FileDataSource(filepath);
//            attachmentPart.setDataHandler(new DataHandler(source));
//            attachmentPart.setFileName("efts.sub");
//
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//            multipart.addBodyPart(attachmentPart);
//
//            message.setContent(multipart);
//
//            Enumeration e = message.getAllHeaderLines();
//            Logger.debug(String.valueOf(e.hasMoreElements()));
//
//            while(e.hasMoreElements()){
//                String param = (String) e.nextElement();
//                System.out.println(param);
//            }
//
//            Transport.send(message);
//            Logger.debug("Sent message successfully....");
//
//
//
//        }
//        catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }













    public static void configureProperties () {
        Logger.debug("Setting properties...");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.pop3.host", "pop3.nosuchhost.nosuchdomain.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("username", "adminhhsalert@gmail.com");
        props.put("password", "P@ssword4");
    }
}
