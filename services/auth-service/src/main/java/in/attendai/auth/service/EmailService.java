package in.attendai.auth.service;

import in.attendai.auth.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService
{
    private final JavaMailSender mailSender;

    private void sendHtmlEmail(String to, String subject, String htmlContent)
    {
        try
        {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("omrakibe30@gmail.com", "AttendAI");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (MessagingException e)
        {
            throw new RuntimeException("Failed to send email.", e);
        } catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String buildEmailTemplate(
            String title,
            String message,
            String highlight,
            String footerMessage
    )
    {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                </head>
                
                <body style="margin:0;padding:0;background:#f4f6f9;font-family:Arial,sans-serif;">
                
                <table width="100%%" cellpadding="0" cellspacing="0" style="padding:40px 0;">
                    <tr>
                        <td align="center">
                
                            <table width="600" cellpadding="0" cellspacing="0"
                                   style="background:#ffffff;border-radius:12px;
                                   overflow:hidden;
                                   box-shadow:0 8px 20px rgba(0,0,0,0.08);">
                
                                <tr>
                                    <td align="center"
                                        style="background:#2563eb;
                                        padding:25px;
                                        color:white;
                                        font-size:28px;
                                        font-weight:bold;">
                
                                        AttendAI
                                    </td>
                                </tr>
                
                                <tr>
                                    <td style="padding:40px;">
                
                                        <h2 style="margin-top:0;color:#1e293b;">
                                            %s
                                        </h2>
                
                                        <p style="font-size:16px;
                                                  color:#475569;
                                                  line-height:1.7;">
                                            %s
                                        </p>
                
                                        <div
                                            style="
                                            background:#eff6ff;
                                            border-left:5px solid #2563eb;
                                            border-radius:8px;
                                            padding:20px;
                                            margin:30px 0;
                                            text-align:center;
                                            font-size:30px;
                                            font-weight:bold;
                                            color:#2563eb;
                                            letter-spacing:3px;">
                
                                            %s
                                        </div>
                
                                        <p style="color:#64748b;
                                                  line-height:1.7;">
                                            %s
                                        </p>
                
                                    </td>
                                </tr>
                
                                <tr>
                                    <td align="center"
                                        style="padding:20px;
                                        background:#f8fafc;
                                        color:#94a3b8;
                                        font-size:13px;">
                
                                        © 2026 AttendAI • Om Rakibe
                
                                    </td>
                                </tr>
                
                            </table>
                
                        </td>
                    </tr>
                </table>
                
                </body>
                </html>
                """.formatted(title, message, highlight, footerMessage);
    }

    @Override
    public void sendOtpEmail(String email, String otp)
    {
        String html = buildEmailTemplate(

                "Verify Your Email",

                "Thank you for registering with AttendAI. Use the OTP below to verify your email address.",

                otp,

                "This OTP is valid for only 5 minutes."
        );

        sendHtmlEmail(email,
                "AttendAI Email Verification",
                html);
    }

    @Override
    public void sendPasswordResetOtp(String email, String otp)
    {
        String html = buildEmailTemplate(

                "Reset Your Password",

                "We received a request to reset your password. Use the OTP below to continue.",

                otp,

                "If you didn't request this, simply ignore this email."
        );

        sendHtmlEmail(email,
                "AttendAI Password Reset",
                html);
    }

    @Override
    public void sendApprovalEmail(User user)
    {

        String html = buildEmailTemplate(

                "🎉 Account Approved",

                "Congratulations, %s! Your registration has been approved successfully."
                        .formatted(user.getFullName()),

                "WELCOME",

                "You can now login using your registered email and password."
        );

        sendHtmlEmail(user.getEmail(),
                "AttendAI Account Approved",
                html);
    }

    @Override
    public void sendRejectionEmail(User user)
    {

        String html = buildEmailTemplate(

                "Registration Update",

                "Hello %s, unfortunately your registration request has been rejected."
                        .formatted(user.getFullName()),

                "REJECTED",

                "If you think this is a mistake, please contact your administrator."
        );

        sendHtmlEmail(user.getEmail(),
                "AttendAI Registration Update",
                html);
    }
}
