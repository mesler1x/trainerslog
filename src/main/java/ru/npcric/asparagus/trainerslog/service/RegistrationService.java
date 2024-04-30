package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.UserRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.common.RegistrationRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth.RegistrationResponse;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class RegistrationService {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) {
        String encodePass = passwordEncoder.encode(registrationRequest.password());

        UserEntity.Context userEntity = new UserEntity.Context(
            registrationRequest.username(),
                encodePass);

        UserEntity user = UserEntity.from(userEntity);
        userRepository.save(user);
        List<String> userRoles = user.getAuthorities().stream().map(Enum::name).toList();
        return new RegistrationResponse(user.getUsername(), user.getPassword(), userRoles);
    }
}
