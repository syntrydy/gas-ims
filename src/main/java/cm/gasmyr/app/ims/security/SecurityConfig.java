package cm.gasmyr.app.ims.security;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor) {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		passwordEncoder.setPasswordEncryptor(passwordEncryptor);
		return passwordEncoder;
	}

	@Bean
	public StrongPasswordEncryptor strongEncryptor() {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor;
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
			UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}

	@Autowired
	public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(
				daoAuthenticationProvider(passwordEncoder(strongEncryptor()), userDetailsService));
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/api/reset", "/", "/css/**", "/js/**", "/fonts/**", "/images/**")
				.permitAll();
		httpSecurity.authorizeRequests().antMatchers("/home", "/users", "/roles", "/groups").hasAnyRole("ADMIN",
				"USER");
		httpSecurity.authorizeRequests().antMatchers("/applications", "/application").hasAnyRole("ADMIN");
		httpSecurity.authorizeRequests().antMatchers("/api/users", "/api/user", "/api/role", "/api/group",
				"/api/application", "/api/measure", "/api/itemmeasure","/api/purchase","/api/item").hasAnyRole("ADMIN", "USER");
		httpSecurity.authorizeRequests().and().formLogin().loginPage("/").usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/home").failureUrl("/");
		httpSecurity.authorizeRequests().and().logout().invalidateHttpSession(true).logoutSuccessUrl("/").permitAll();
		httpSecurity.authorizeRequests().anyRequest().authenticated();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(false);
	}
}
