package in.attendai.apigateway.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService)
    {
        this.jwtService = jwtService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
    {

//        System.out.println("URI        : " + request.getRequestURI());
//        System.out.println("ServletPath: " + request.getServletPath());

        String path = request.getServletPath();

        return path.startsWith("/api/auth/login")
                || path.startsWith("/api/auth/register")
                || path.startsWith("/api/auth/verify-otp")
                || path.startsWith("/api/auth/forgot-password")
                || path.startsWith("/api/auth/reset-password");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException
    {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer "))
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String jwt = authHeader.substring(7);

        if (!jwtService.isTokenValid(jwt))
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String username = jwtService.extractUsername(jwt);
        String role = jwtService.extractRole(jwt);

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + role));

        CurrentUser currentUser = new CurrentUser(
                jwtService.extractUserId(jwt),
                jwtService.extractEmail(jwt),
                jwtService.extractRole(jwt)
        );

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        currentUser,
                        null,
                        authorities
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

//        System.out.println(SecurityContextHolder.getContext().getAuthentication());
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        filterChain.doFilter(request, response);

//        System.out.println("URI: " + request.getRequestURI());
//
////        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        System.out.println("Authorization: " + authHeader);
//        System.out.println("JWT Valid: " + jwtService.isTokenValid(jwt));
//        System.out.println("Role: " + jwtService.extractRole(jwt));
    }
}
