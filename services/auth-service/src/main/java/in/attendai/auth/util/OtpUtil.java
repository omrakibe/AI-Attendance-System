package in.attendai.auth.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil
{
    public String generateOtp()
    {
        Random random = new Random();

        return String.valueOf(
                100000 + random.nextInt(900000)
        );
    }
}
