package howmuch.com.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import howmuch.com.dto.UsersDTO;
import howmuch.com.handler.CustomAuthenticationProvider;
import howmuch.com.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig { 
	
	@Autowired
    private CustomAuthenticationProvider authProvider;
	private final UserRepository userRepository;
	private final UserDetailsService userDetailsService;
    public SecurityConfig(UserDetailsService userDetailsService, UserRepository userRepository) {
    	this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }
    
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
	        requestHandler.setCsrfRequestAttributeName("_csrf");
	        
		http
			.authorizeHttpRequests((authorizeRequests) ->
				authorizeRequests
					.requestMatchers(
							"/api/register", 
							"/api/login", 
							"/api/findPw",
							"/api/sendverifyemail",
							"/api/validationcheckemail",
							"/api/mailCheck",
							"/error").permitAll()
					.requestMatchers("/api/unit/**").hasRole("USER")
					.requestMatchers("/api/Ingredients/**").hasRole("USER")
					.requestMatchers("/api/margin/**").hasRole("USER")
					.requestMatchers("/api/recipe/**").hasRole("USER")
					.requestMatchers("/api/source/**").hasRole("USER")
					.requestMatchers("/api/subscription/**").hasRole("USER")
					.requestMatchers("/api/stock/**").hasRole("USER")
					.anyRequest()
					.authenticated()
			)
			.logout((logout) -> logout.logoutUrl("/api/logout"));
		
		return http.
				sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer 
		                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				.cors((cors) -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        return config;
                    }
                })).
				csrf((csrf) -> csrf
                        // CSRF 토큰 요청 핸들러 설정
                        .csrfTokenRequestHandler(requestHandler)
                        // /register 경로에 대해 CSRF 보호 비활성화
                        .ignoringRequestMatchers(
                        		"/api/register", 
                        		"/api/findPw", 
                        		"/api/login",
                        		"/api/sendverifyemail",
    							"/api/validationcheckemail",
    							"/api/mailCheck"
                        )
                        // CSRF 토큰을 쿠키로 저장, HttpOnly 설정 비활성화
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				.build();
	}
	
//	@Bean
//	public AuthenticationSuccessHandler authenticationSuccessHandler() {
//	   return new CustomAuthenticationSuccessHandler();
//	}
//
//	@Bean
//	public AccessDeniedHandler accessDeniedHandler() {
//	   return new CustomAccessDeniedHandler();
//	}
	@Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }
	
	@Bean
    public UserDetailsService userDetailsService() {
        return userId -> {
            // 데이터베이스에서 사용자 조회 (예시: userRepository)
            UsersDTO userEntity = userRepository.findByUserId(userId);

            // UserEntity 객체에서 Spring Security의 UserDetails 객체로 변환
            return User.builder()
                    .username(userEntity.getUserId())
                    .password(userEntity.getPassword()) // 이미 BCrypt로 암호화되어 있다고 가정
                    .roles(userEntity.getROLE()) // 예: ROLE_USER
                    .build();
        };
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
	
}
