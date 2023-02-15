package thanu;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalDateTime;
import java.util.Properties;

public class Mail {

    private static int noOfMailSend;

    private LocalDateTime todayDate = LocalDateTime.now();
    private String toMailID;
    private String subject;
    private String body;
    public Mail( String toMailID, String subject, String body) {
        this.toMailID = toMailID; // set Recipient's email ID
        this.subject = subject;  // set email subject
        this.body = body;  // set the email body
        noOfMailSend = getNoOfMailSend() +1;
    }

    //mail is send when the method is call
    public void sendMail(){
        final String username = "example@gmail.com";
        final String password = "mail app password";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("example@gmail.com"));      //set the from mail
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toMailID)  // set the to mail ID
            );
            message.setSubject(subject);  // set the subject of sending mail
           // message.setText(body);  // set the body of sending mail

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setContent(body, "text/html; charset=utf-8");  //set the body and set the text editor is html

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();


            //set the attachment file
            DataSource source = new FileDataSource("D:\\lasu\\EmailClientLasu\\Resume.pdf");
//            DataSource source = new FileDataSource("D:\\lasu\\EmailClientLasu\\sampleAttachment.pdf");

            messageBodyPart.setDataHandler(new DataHandler(source));

            //set the attachment file name
            messageBodyPart.setFileName("Resume.pdf");
//            messageBodyPart.setFileName("SampleAttach.pdf");
            multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("mail sending is finished");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    //return the no of mail send now
    public static int getNoOfMailSend (){
        return noOfMailSend;
    }


}
