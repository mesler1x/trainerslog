package ru.npcric.asparagus.trainerslog.adapter.web.common;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth.UserResponse;
import ru.npcric.asparagus.trainerslog.service.common.UserService;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    UserService userService;
    @GetMapping("/get")
    public UserResponse getAuth() {
        return userService.getCurrentUser();
    }
}
