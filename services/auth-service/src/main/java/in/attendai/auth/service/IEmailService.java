package in.attendai.auth.service;

public interface IEmailService
{

    void sendSimpleEmail(
            String to,
            String subject,
            String body
    );

    void sendOtpEmail(String email, String otp);

    void sendPasswordResetOtp(String email, String otp);

}
