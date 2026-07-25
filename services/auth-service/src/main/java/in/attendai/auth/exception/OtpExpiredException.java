package in.attendai.auth.exception;

public class OtpExpiredException extends RuntimeException
{
    public OtpExpiredException(String s)
    {
        super(s);
    }
}
