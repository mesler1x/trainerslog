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
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth.RegistrationResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.AlreadyExistException;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class RegistrationService {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;


    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) {
        String encodePass = passwordEncoder.encode(registrationRequest.password());
        Optional<UserEntity> checkUsername = userRepository.findByUsername(registrationRequest.username());
        if (checkUsername.isPresent()) {
            throw new AlreadyExistException("User");
        }

        UserEntity.Context userEntity = new UserEntity.Context(
            registrationRequest.username(),
                encodePass);

        UserEntity user = UserEntity.from(userEntity);
        userRepository.save(user);

        List<String> userRoles = user.getAuthorities().stream().map(Enum::name).toList();
        return new RegistrationResponse(user.getUsername(), user.getPassword(), userRoles);
    }
}
