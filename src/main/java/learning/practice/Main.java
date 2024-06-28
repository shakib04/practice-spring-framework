package learning.practice;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

@SpringBootApplication
public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @RestController
  @RequestMapping("api/public")
  public class HelloRestController {

    @GetMapping("user")
    public String helloUser(HttpServletRequest request) {
      log.info(request.toString());
      return "Hello User";
    }

    @GetMapping("admin")
    public String helloAdmin() {
      return "Hello Admin";
    }
  }

  @Component
  public class JwtTokenUtil {

    public boolean validate(String token) {
      return true;
    }

    public String generateAccessToken(User user) {
      return "";
    }
  }

  @Component
  public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain)
        throws ServletException, IOException {
      // Get authorization header and validate
      final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
      if (isEmpty(header) || !header.startsWith("Bearer ")) {
        chain.doFilter(request, response);
        return;
      }

      // Get jwt token and validate
      final String token = header.split(" ")[1].trim();
      if (!jwtTokenUtil.validate(token)) {
        chain.doFilter(request, response);
        return;
      }

      // Get user identity and set it on the spring security context
      UserDetails userDetails = new User("admin", "admin",
          Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

      UsernamePasswordAuthenticationToken
          authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null,
          userDetails.getAuthorities()
      );

      authentication.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(request, response);
    }

  }


  @EnableWebSecurity
  public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      // TODO configure authentication manager
      log.info(auth.toString());
      auth.userDetailsService(username -> {
        if (username == null) {
          throw new UsernameNotFoundException("User not found");
        } else {
          return new User("admin", "admin",
              Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }
      });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      // Enable CORS and disable CSRF
      http = http.cors().and().csrf().disable();

      // Set session management to stateless
      http = http
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and();

      // Set unauthorized requests exception handler
      http = http
          .exceptionHandling()
          .authenticationEntryPoint(
              (request, response, ex) -> {
                response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    ex.getMessage()
                );
              }
          )
          .and();

      // Set permissions on endpoints
      http.authorizeRequests()
          // Our public endpoints
          .antMatchers("/api/public/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
          .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
          .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
          .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()
          // Our private endpoints
          .anyRequest().authenticated();

      // Add JWT token filter
      http.addFilterBefore(
          jwtTokenFilter,
          UsernamePasswordAuthenticationFilter.class
      );
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
  }

  public class UserView {

    private String username;
    private String password;
    private String role;

    public UserView() {
    }

    public UserView(String username, String password, String role) {
      this.username = username;
      this.password = password;
      this.role = role;
    }

    public String getUsername() {
      return username;
    }
  }

  @RestController
  @RequestMapping(path = "api/public")
  public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthApi(AuthenticationManager authenticationManager,
        JwtTokenUtil jwtTokenUtil) {
      this.authenticationManager = authenticationManager;
      this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest request) {
      try {
        Authentication authenticate = authenticationManager
            .authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
                )
            );

        User user = (User) authenticate.getPrincipal();

        return ResponseEntity.ok()
            .header(
                HttpHeaders.AUTHORIZATION,
                jwtTokenUtil.generateAccessToken(user)
            )
            .body(user);
      } catch (BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
    }

  }
}
