package ru.npcric.asparagus.trainerslog.adapter.web;

import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.npcric.asparagus.trainerslog.service.FilialService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/trainerslog/api/v1/filial")
@RolesAllowed("DEFAULT")
public class FilialController {
    FilialService filialService;

}
