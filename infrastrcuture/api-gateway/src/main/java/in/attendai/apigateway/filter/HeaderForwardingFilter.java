package in.attendai.apigateway.filter;

import in.attendai.apigateway.security.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class HeaderForwardingFilter
{

    public HandlerFilterFunction<ServerResponse, ServerResponse> forwardHeaders()
    {

        return (request, next) ->
        {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null &&
                    authentication.getPrincipal() instanceof CurrentUser currentUser)
            {

                ServerRequest modifiedRequest = ServerRequest.from(request)
                        .header("X-User-Id", currentUser.userId().toString())
                        .header("X-Email", currentUser.email())
                        .header("X-Role", currentUser.role())
                        .build();

                return next.handle(modifiedRequest);
            }

            return next.handle(request);
        };
    }
}
