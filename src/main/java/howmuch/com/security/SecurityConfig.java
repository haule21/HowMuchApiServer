package howmuch.com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import howmuch.com.dto.UsersDTO;
import howmuch.com.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final UserRepository userRepository;
	private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService, UserRepository userRepository) {
    	this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }
    
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests((authorizeRequests) ->
				authorizeRequests
					.requestMatchers("/api/register", "/api/login", "/error").permitAll()
					.requestMatchers("/api/unit/**").hasRole("USER")
					.requestMatchers("/api/Ingredients/**").hasRole("USER")
					.requestMatchers("/api/margin/**").hasRole("ADMIN")
					.requestMatchers("/api/recipe/**").hasRole("USER")
					.requestMatchers("/api/source/**").hasRole("USER")
					.requestMatchers("/api/stock/**").hasRole("ADMIN")
					.anyRequest()
					.authenticated()
			);
		
		return http.
				sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer 
		                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)).
				csrf(AbstractHttpConfigurer::disable).
				build();
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
