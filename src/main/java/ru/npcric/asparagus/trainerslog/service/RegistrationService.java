package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.UserRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.common.RegistrationRequest;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class RegistrationService {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public ResponseEntity<?> registerUser(RegistrationRequest registrationRequest) {
        String encodePass = passwordEncoder.encode(registrationRequest.password());

        UserEntity.Context userEntity = new UserEntity.Context(
            registrationRequest.username(),
                encodePass);

        UserEntity user = UserEntity.from(userEntity);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
