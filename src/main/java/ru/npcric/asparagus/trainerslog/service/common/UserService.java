package ru.npcric.asparagus.trainerslog.service.common;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.UserRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth.UserResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.user.UserSmallResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
            List<String> userRolesResponse = new ArrayList<>();
            for(GrantedAuthority role : roles) {
                userRolesResponse.add(role.getAuthority());
            }
            return new UserResponse(username, userRolesResponse);
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> person = userRepository.findByUsername(username);

        if (person.isEmpty())
            throw new UserNotFoundException(username);

        return person.get();
    }

    public List<UserSmallResponse> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity -> new UserSmallResponse(userEntity.getId(),userEntity.getUsername())).toList();
    }

    public UserResponse addAdminRoleToExistingUser(String username) {
        UserEntity user = (UserEntity) loadUserByUsername(username);
        user.getAuthorities().add(UserRole.ROLE_ADMIN);
        return new UserResponse(user.getUsername(), user.getAuthorities().stream().map(Enum::name).toList());
    }
}
