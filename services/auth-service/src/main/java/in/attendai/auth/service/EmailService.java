package in.attendai.auth.service;

import in.attendai.auth.entity.User;
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

    @Override
    public void sendPasswordResetOtp(String email, String otp)
    {
        String subject = "AttendAI Password Reset OTP";

        String body = """
                Hello,
                
                We received a request to reset your password.
                
                Your OTP is:
                
                %s
                
                This OTP is valid for 5 minutes.
                
                If you didn't request a password reset, you can safely ignore this email.
                
                Regards,
                AttendAI Team
                """.formatted(otp);

        sendSimpleEmail(email, subject, body);
    }

    @Override
    public void sendApprovalEmail(User user)
    {

        String subject = "Your AttendAI Account Has Been Approved";

        String body = """
                Dear %s,
                
                Congratulations!
                
                Your registration request has been approved.
                
                You can now log in to AttendAI using your registered email and password.
                
                We’re excited to have you on board.
                
                Regards,
                AttendAI Team
                """.formatted(user.getFullName());

        sendSimpleEmail(user.getEmail(), subject, body);
    }

    @Override
    public void sendRejectionEmail(User user)
    {

        String subject = "Update on Your AttendAI Registration";

        String body = """
                Dear %s,
                
                Thank you for registering with AttendAI.
                
                After reviewing your registration request, we regret to inform you that it has been rejected.
                
                If you believe this was a mistake or need further clarification, please contact the administrator.
                
                Regards,
                AttendAI Team
                """.formatted(user.getFullName());

        sendSimpleEmail(user.getEmail(), subject, body);
    }
}
