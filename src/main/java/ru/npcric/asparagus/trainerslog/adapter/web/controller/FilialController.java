package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.filial.FilialSmallResponse;
import ru.npcric.asparagus.trainerslog.service.FilialService;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/trainerslog/api/v1/filial")
@Tag(name = "Контроллер филиалов", description = "Контроллер для управления филиалами федерации айкидо")
public class FilialController {
    FilialService filialService;

    @RolesAllowed("ADMIN")
    @Operation(
            summary = "Создание нового филиала",
            description = "Создание нового филиала администрацией федерации айкидо"
    )
    @PostMapping("/create")
    public ResponseEntity<FilialSmallResponse> createFilial(@Valid @RequestBody FilialDTO filialDTO) {
        return ResponseEntity.ok().body(filialService.create(filialDTO));
    }

    @Operation(
            summary = "Получение всех филиалов федерации айкидо",
            description = "Получение id, именён и адресов всех филиалов федерации"
    )
    @GetMapping("/getAll")
    public ResponseEntity<List<FilialSmallResponse>> getAllFilial(){
        return ResponseEntity.ok().body(filialService.getAll());
    }

    @Operation(
            summary = "Удаление филиала по его адресу",
            description = "Филиалы тренеров, учеников после удаления их филиала будут ссылаться на null"
    )
    @RolesAllowed("ADMIN")
    @DeleteMapping("/deleteByAddress")
    public ResponseEntity<?> deleteFilialByAddress(@RequestParam("address") String address){
        filialService.deleteFilialByAddress(address);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
