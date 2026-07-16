package in.attendai.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService
{
    private final JavaMailSender mailSender;

    @Override
    public void sendSimpleEmail(String to, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @Override
    public void sendOtpEmail(String email, String otp)
    {
        String subject = "AttendAI Email Verification OTP";

        String body = """
                Hello,
                
                Your OTP for email verification is:
                
                %s
                
                This OTP is valid for 5 minutes.
                
                If you did not initiate this request, please ignore this email.
                
                Regards,
                AttendAI Team
                """.formatted(otp);

        sendSimpleEmail(email, subject, body);
    }
}
