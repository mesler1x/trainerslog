package ru.npcric.asparagus.trainerslog.service.common;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth.UserResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
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
}
