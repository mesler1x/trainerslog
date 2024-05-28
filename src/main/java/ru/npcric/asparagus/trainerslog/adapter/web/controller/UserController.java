package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth.UserResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.user.UserSmallResponse;
import ru.npcric.asparagus.trainerslog.service.common.UserService;

import java.util.List;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/trainerslog/api/v1/user")
@Tag(name = "Контроллер пользователей")
public class UserController {
    UserService userService;

    @Operation(
            summary = "Просмотр всех пользователей системы"
    )
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserSmallResponse>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @Operation(
            summary = "Просмотр аутентифицированного пользователя"
    )
    @GetMapping("/getCurrent")
    public UserResponse getAuth() {
        return userService.getCurrentUser();
    }

    @Operation(
            summary = "Назначить нового администратора"
    )
    @PatchMapping("/createAdmin")
    public UserResponse createAdmin(@RequestParam String username) {
        return userService.addAdminRoleToExistingUser(username);
    }
}
