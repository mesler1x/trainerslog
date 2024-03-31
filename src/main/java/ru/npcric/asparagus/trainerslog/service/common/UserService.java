package ru.npcric.asparagus.trainerslog.service.common;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
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
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
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
            throw new UsernameNotFoundException("User not found");

        return person.get();
    }
}
