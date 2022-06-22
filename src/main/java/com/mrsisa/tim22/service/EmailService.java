package com.mrsisa.tim22.service;

import com.mrsisa.tim22.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;


    public void sendEmail(String recieverEmail, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fishnships266@gmail.com");
        message.setTo(recieverEmail);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
    public void sendActivationEmail(User user){
        sendEmail(user.getUsername(),"Fish&Ships actiovation email","Confirm your email: http://localhost:8080/auth/activate/" + user.getId().toString());
    }

    public void sendOwnerActivationEmail(String email){
        sendEmail(email, "Fish&Ships activation successful!", "Your Fisn&Ships account was approved by administrator! You are free to use all the services that Fish&Ships provides.");
    }

    public void sendPromoEmail(String email, String entityType, String entityName){
        sendEmail(email, "New promotion!", "The " + entityName +" you are subscribed just published a new promotion! Check out "+ entityName+"'s promotion at your subscription list!");
    }

    public void sendReviewEmail(String email, String reviewText, String clientName, int score, String entityName){
        sendEmail(email, "New review for " + entityName +"!", entityName + " has just recieved a new review! It got a " + score + " score from " + clientName + "\nReview text:\n" + reviewText);
    }
    public void sendConfirmReservationEmail(String email, String entityName){
        sendEmail(email, "Your reservation is confirmed!", "Your reservation at " + entityName + " is confirmed!");
    }
    public void sendComplaintEmail(String clientEmail, String ownerEmail, String complaintText, String responseText){
        sendEmail(clientEmail, "Complaint about " + ownerEmail, "System administrator responded to your complaint, you can check it out in further text.\n"+"Complaint text:\n"+complaintText+"\nResponse text:\n" + responseText);
        sendEmail(ownerEmail, "Complaint from" + clientEmail, "You recieved a complaint made by " + clientEmail + ". You can check it out in further text, as well as administrator response.\n"+"Complaint text:\n"+complaintText+"\nResponse text:\n" + responseText);
    }
    public void deleteRequestApprovedEmail(String email){
        sendEmail(email, "Profile delete request approved!", "Your request for account deletion was approved by administration admin. You no longer have the ability to use our services. Respond to this email if you ever wish to reopen your account");
    }
    public void sendReservationReportAccepted(String clientEmail, String ownerEmail, String complaintText, String responseText){
        sendEmail(ownerEmail, "Complaint about " + clientEmail, "Your complaint has been accepted and client "+ clientEmail +" will recive penalty.You can check it out in further text.\n"+"Complaint text:\n"+complaintText+"\nResponse text:\n" + responseText);
        sendEmail(clientEmail, "Complaint from" + ownerEmail, "You recieved a complaint made by " + ownerEmail + ". Complaint has been accepted and you will recive penalty. You can check it out in further text, as well as administrator response.\n"+"Complaint text:\n"+complaintText+"\nResponse text:\n" + responseText);
    }

    public void sendReservationReportDeclined(String clientEmail, String ownerEmail, String complaintText, String responseText) {
        sendEmail(ownerEmail, "Complaint about " + clientEmail, "Your complaint has been declined and client "+ clientEmail +" will  not recive any penalties.You can check it out in further text.\n"+"Complaint text:\n"+complaintText+"\nResponse text:\n" + responseText);
        sendEmail(clientEmail, "Complaint from" + ownerEmail, "You recieved a complaint made by " + ownerEmail + ". Complaint has been declined and you will not recive  any penalties. You can check it out in further text, as well as administrator response.\n"+"Complaint text:\n"+complaintText+"\nResponse text:\n" + responseText);

    }
}
