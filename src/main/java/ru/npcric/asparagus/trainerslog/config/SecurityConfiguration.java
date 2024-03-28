package ru.npcric.asparagus.trainerslog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.npcric.asparagus.trainerslog.domain.user.UserRole;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    static String[] PERMITED_URI = {"/trainerslog/api/v1/public/**"};
    static String[] ADMIN_URI = {"/trainerslog/api/v1/admin/**"};
    static String[] COACH_URI = {"/trainerslog/api/v1/coach/**"};
    static String[] STUDENT_URI = {"/trainerslog/api/v1/student/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .requestCache(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(c -> c.requestMatchers(PERMITED_URI)
                        .permitAll()
                        .requestMatchers(COACH_URI).hasAnyAuthority(UserRole.COACH.name())
                        .requestMatchers(ADMIN_URI).hasAnyAuthority(UserRole.ADMIN.name())
                        .requestMatchers(STUDENT_URI).hasAnyAuthority(UserRole.STUDENT.name())
                        ).httpBasic(httpSecurityHttpBasicConfigurer -> {}).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
