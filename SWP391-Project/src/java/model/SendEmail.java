/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dal.CourseDAO;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    public static void send(String to, String sub,
            String msg, final String user, final String pass) {
        //create an instance of Properties Class   
        Properties props = new Properties();

        /* Specifies the IP address of your default mail server
     	   for e.g if you are using gmail server as an email sever
           you will pass smtp.gmail.com as value of mail.smtp host. 
           As shown here in the code. 
           Change accordingly, if your email id is not a gmail id
         */
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com");
        //below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        /* Pass Properties object(props) and Authenticator object   
           for authentication to Session instance 
         */
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {

            /* Create an instance of MimeMessage, 
 	      it accept MIME types and headers 
             */
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");

            /* Transport class is used to deliver the message to the recipients */
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //random code
    public static String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    //send verification code
    public static void sendVerifyCode(String emailTo, String fname, String code) {
        String subject = "Verify Account";
        String message = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "<style>\n"
                + ".hello {color:black; font-weight:bold;}\n"
                + ".suc {color: green; font-style: italic; font-weight:bold;}\n"
                + ".pass {color: black; font-weight:bold; font-style:italic}\n"
                + ".bye {color: blue;}"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h4 class='hello'>Hello "+fname+",</h4>\n"
                + "<p>You have already registered a new account <span class='suc'>successfully</span> by gmail " +emailTo+ " from our system.</p>\n"
                + "<p>And now, please verify to be active your account by using this code: <span class='pass'>"+code+"</span>."
                + "<h4>Best regrads,</h4>"
                + "<h3 class='bye'>Coursere</h3>"
                + "</body>"
                + "</html>";
        SendEmail.send(emailTo, subject, message, "thanhpnhe151285@fpt.edu.vn", "thanhthai");
    }
    //send registered
    public static void sendRegister(String emailTo, String fname, String pass) {
        String subject = "Registered Account";
        String message = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "<style>\n"
                + ".hello {color:black; font-weight:bold;}\n"
                + ".suc {color: green; font-style: italic; font-weight:bold;}\n"
                + ".warn {color: red; font-weight:bold;}\n"
                + ".pass {color: black; font-weight:bold; font-style:italic}\n"
                + ".bye {color: blue;}"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h4 class='hello'>Hello "+fname+",</h4>\n"
                + "<p>You have already verifed a new account <span class='suc'>successfully</span> by gmail "+emailTo+" from our system.</p>\n"
                + "<p>Thanks for joining with us. So I hope you will try to gain <span class='suc'>high achievements</span> in the road ahead.</p>\n"
                + "<p>And now, you can login to our system with password: <span class='pass'>"+pass+"</span>."
                + "<p><span class='warn'>Note:</span> Please <span class='warn'>do not share</span> your password to anyone."
                + "<h4>Best regrads,</h4>"
                + "<h3 class='bye'>Coursere</h3>"
                + "</body>"
                + "</html>";
        SendEmail.send(emailTo, subject, message, "thanhpnhe151285@fpt.edu.vn", "thanhthai");
    }
    
    //Reset Password
    public void resetPW(String emailTo, String newpass) {
        String subject = "COURSERE";
        String message = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div>\n"
                + "        <h2><b>RESET PASSWORD</b></h2>\n"
                + "        <p>Your new password is <b>" + newpass + "</b>. <br><span style=\"color: red;\"><b>Don't share this password with anyone!</b></span></p>\n"
                + "        <p>Thanks and best regards,<br>\n"
                + "            ----------------------------<br>\n"
                + "            COURSERE\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        SendEmail.send(emailTo, subject, message, "thanhpnhe151285@fpt.edu.vn", "thanhthai");
    }
    
    //insert new user
    public void insertUser(String emailTo, String pass) {
        String subject = "COURSERE";
        String message = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div>\n"
                + "        <h2><b>You have been created an account in COURSERE</b></h2>\n"
                + "        <p>Your password is <b>" + pass + "</b> .<br><span style=\"color: red;\"><b>Don't share this password with anyone!</b></span></p>\n"
                + "        <p>Thanks and best regards,<br>\n"
                + "            ----------------------------<br>\n"
                + "            COURSERE\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        SendEmail.send(emailTo, subject, message, "thanhpnhe151285@fpt.edu.vn", "thanhthai");
    }

    //Send Registration
    public void sendRegistration(String emailTo, String cid, String packprice) {
        CourseDAO cd = new CourseDAO();
        Course c = cd.getCourseById(cid);
        double price = 0.0;
        if (packprice.equals("Free")) {
            price = 0.0;
        } else if (packprice.equals("Silver")) {
            price = c.getCprice();
        } else if (packprice.equals("Gold")) {
            price = c.getCprice() * 1.2;
        } else if (packprice.equals("Diamond")) {
            price = c.getCprice() * 1.6;
        }

        String subject = "COURSERE";
        String message = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "\n"
                + "<head>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <div>\n"
                + "        <h2 style=\"color: green;\"><b>REGISTERED SUCCESSFULLY</b></h2>\n"
                + "        <p>You have just successfully registered for our <span style=\"color: blue;\"><b>" + c.getCname() + "</b></span> course.<br>\n"
                + "\n";

        if (packprice.equals("Free")) {
            message += "            The <span style=\"color: blue;\"><b>"+c.getCname()+"</b></span> course you just registered  for is a <span\n"
                    + "                style=\"color: green;\"><b>"+packprice+"</b></span> course.<br>\n"
                    + "            <br>\n";
        } else {
            message += "            Because this course is the <span style=\"color: red;\"><b>PAID</b></span> course, you bought our <span\n"
                    + "                style=\"color: blue;\"><b>"+c.getCname()+"</b></span> course with a package price <span\n"
                    + "                style=\"color: blue;\"><b>"+packprice+"</b></span> equivalent to\n"
                    + "            an amount of <span style=\"color: red;\"><b>"+price+"$</b></span>.<br>\n"
                    + "            <br>\n";
        }
        
        message += "            <span style=\"color: blue;\"><i>COURSERE wishes learners a great experience with our course.</i></span><br><br>\n"
                + "            Thanks and best regards,<br>\n"
                + "            ----------------------------<br>\n"
                + "            COURSERE\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        SendEmail.send(emailTo, subject, message, "thanhpnhe151285@fpt.edu.vn", "thanhthai");
    }
}
