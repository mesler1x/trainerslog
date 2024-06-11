package ru.npcric.asparagus.trainerslog.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.npcric.asparagus.trainerslog.domain.user.UserRole;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SecurityConfig {

    public static String[] PERMITTED_URI = {
            "/trainerslog/api/v1/public/**",
            "/trainerslog/api/v1/coach/getCoachesInFilial",
            "/trainerslog/api/v1/filial/getAll",
            "/trainerslog/api/v1/user/getCurrent",
    };


    public static String[] ADMIN_URI = {
        "/**"
    };

    public static String[] STUDENT_COACH_COMMON = {
            "/trainerslog/api/v1/attendance/markAttendance",
            "/trainerslog/api/v1/attendance/monthlyAttendance",
            "/trainerslog/api/v1/coach/getCoachesInFilial",
            "/trainerslog/api/v1/student/getStudentsInGroup",
            "/trainerslog/api/v1/student/getUsernameOfStudent",
    };

    public static String[] STUDENT_URI = {
            "/trainerslog/api/v1/cheque/add",
            "/trainerslog/api/v1/cheque/getByAuth",
            "/trainerslog/api/v1/cheque/delete",
            "/trainerslog/api/v1/student/getByAuth",
            "/trainerslog/api/v1/student/updateStudentInfo",
    };

    public static String[] COACH_URI = {
            "/trainerslog/api/v1/cheque/confirm",
            "/trainerslog/api/v1/cheque/get",

            "/trainerslog/api/v1/student/deleteStudentFromGroup",
            "/trainerslog/api/v1/student/get",
            "/trainerslog/api/v1/student/addStudentInExistingGroup",
            "/trainerslog/api/v1/student/getStudentsInGroup",
            "/trainerslog/api/v1/student/getStudentsInGroupWithDebts",


            "/trainerslog/api/v1/coach/getAllCoaches",
            "/trainerslog/api/v1/coach/getCoachGroups",
            "/trainerslog/api/v1/coach/getAllCoachStudents",
            "/trainerslog/api/v1/coach/updateCoachInfo",
            "/trainerslog/api/v1/coach/getByAuth",
            "/trainerslog/api/v1/coach/getUsernameOfCoach",

            "/trainerslog/api/v1/group/createGroup",
            "/trainerslog/api/v1/group/deleteGroup",


            "/trainerslog/api/v1/ticket/updateToPaid",
            "/trainerslog/api/v1/training/createTraining",
            "/trainerslog/api/v1/training/deleteTraining",
            "/trainerslog/api/v1/training/getGroupTrainingsForWeek",
            "/trainerslog/api/v1/training/getGroupTrainingsForWeek/updateTrainingTime",
            "/trainerslog/api/v1/training/getGroupTrainingsForWeek/updateTrainingComment",

            "/trainerslog/api/v1/attendance/getGroupWeekAttendance"
    };




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .requestCache(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(c -> c
                        .requestMatchers(PERMITTED_URI)
                        .permitAll()
                        .requestMatchers(STUDENT_COACH_COMMON)
                        .hasAnyAuthority(UserRole.ROLE_STUDENT.name(), UserRole.ROLE_COACH.name(), UserRole.ROLE_ADMIN.name())
                        .requestMatchers(STUDENT_URI)
                        .hasAnyAuthority(UserRole.ROLE_STUDENT.name(), UserRole.ROLE_ADMIN.name())
                        .requestMatchers(COACH_URI)
                        .hasAnyAuthority(UserRole.ROLE_COACH.name(), UserRole.ROLE_ADMIN.name())
                        .requestMatchers(ADMIN_URI)
                        .hasAuthority(UserRole.ROLE_ADMIN.name())

                ).httpBasic(
                        Customizer.withDefaults()
                ).build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowPrivateNetwork(true);
        configuration.setAllowedOriginPatterns(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
