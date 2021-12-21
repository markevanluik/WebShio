package ee.mark.webshiospring.configuration;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Log4j2
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private String secretKey;

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            log.info("Starting to decode");
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(secretKey.getBytes())
                        .parseClaimsJws(token.replace("Bearer ", ""))
                        .getBody();
            } catch (ExpiredJwtException e) {
                log.error("Expired JSON WEB TOKEN {} {}", token, e.getMessage());
            } catch (UnsupportedJwtException e) {
                log.error("Wrong format JSON WEB TOKEN {} {}", token, e.getMessage());
            } catch (MalformedJwtException e) {
                log.error("Modified JSON WEB TOKEN {} {}", token, e.getMessage());
            } catch (SignatureException e) {
                log.error("Wrong signature JSON WEB TOKEN {} {}", token, e.getMessage());
            } catch (IllegalArgumentException e) {
                log.error("Invalid JSON WEB TOKEN {} {}", token, e.getMessage());
            }

            String email = claims.getSubject();
            String issuer = claims.getIssuer();
            Date expiration = claims.getExpiration();
            if(expiration.compareTo(new Date()) > 0 && issuer.equals("webshio")) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("logged in");
            }
        }
        chain.doFilter(request, response);
    }
}
