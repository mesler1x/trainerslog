package ru.npcric.asparagus.trainerslog.adapter.web;

import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth.UserResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.user.UserSmallResponse;
import ru.npcric.asparagus.trainerslog.service.common.UserService;

import java.util.List;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/trainerslog/api/v1/user")
//@RolesAllowed("ADMIN")
public class UserController {
    //проверено
    UserService userService;

    @GetMapping("/getAllUsers")
    public List<UserSmallResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getCurrent")
    public UserResponse getAuth() {
        return userService.getCurrentUser();
    }
}
