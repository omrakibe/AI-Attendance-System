package in.attendai.auth.service;

import in.attendai.auth.entity.User;

public interface IEmailService
{

//    void sendSimpleEmail(
//            String to,
//            String subject,
//            String body
//    );

    void sendOtpEmail(String email, String otp);

    void sendPasswordResetOtp(String email, String otp);

    void sendApprovalEmail(User user);

    void sendRejectionEmail(User user);

}
