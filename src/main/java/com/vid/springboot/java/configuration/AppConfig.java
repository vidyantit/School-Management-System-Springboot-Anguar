package com.vid.springboot.java.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vid.springboot.java.filters.JwtRequestFilter;
import com.vid.springboot.java.security.JwtAuthenticationEntryPoint;
import com.vid.springboot.java.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class AppConfig {
	
	
	 @Autowired	
		private JwtAuthenticationEntryPoint point;
		
	     @Autowired
		private JwtAuthenticationFilter filter;
	     
	     @Autowired
	     private final JwtRequestFilter jwtRequestFilter;
	     

	public AppConfig(JwtRequestFilter jwtRequestFilter) {
			super();
			this.jwtRequestFilter = jwtRequestFilter;
		}

	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * 
	 * UserDetails
	 * user=User.builder().username("singh").password(passwordEncoder().encode(
	 * "java")).roles("admin").build(); UserDetails
	 * user1=User.builder().username("john").password(passwordEncoder().encode(
	 * "oracle")).roles("admin").build(); return new
	 * InMemoryUserDetailsManager(user,user1); }
	 */
	
	 @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         http.csrf(csrf -> csrf.disable())
         .cors(cors->cors.disable())
         .authorizeHttpRequests(auth->auth.requestMatchers("authenticate").permitAll()
         .requestMatchers("/api/**").permitAll()
         .anyRequest().authenticated())
         .exceptionHandling(ex->ex.authenticationEntryPoint(point))
         .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
         
         http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
                 
         return http.build();
     }
	 
	
	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception{ return http.csrf().disable() .authorizeHttpRequests()
	 * .requestMatchers("/authenticate").permitAll() .and()
	 * .authorizeHttpRequests().requestMatchers("/api/**") .authenticated().and()
	 * .sessionManagement() .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	 * .and() .addFilterBefore(jwtRequestFilter,
	 * UsernamePasswordAuthenticationFilter.class) .build(); }
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
	
	
}