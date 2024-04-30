package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.filial.FilialSmallResponse;
import ru.npcric.asparagus.trainerslog.service.FilialService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FilialControllerTest {
    @Mock
    FilialService filialService;
    @InjectMocks
    FilialController filialController;

    @Test
    @DisplayName("POST /trainerslog/api/v1/filial/create проверяет создание филиала")
    void createFilial_ReturnsValidResponseEntity() {
        //given
        var filialRequest = new FilialDTO("Юго Западный филиал", "Фурманова 125");
        var filialResponse = new FilialSmallResponse(1L, "Юго Западный филиал", "Фурманова 125");
        Mockito.doReturn(filialResponse).when(this.filialService).create(filialRequest);
        //when
        var responseEntity = this.filialController.createFilial(filialRequest);
        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filialResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("GET /trainerslog/api/v1/filial/getAll проверяет наличие всех филиалов")
    void getAllFilial_ValidatingList_ReturnsValidResponseEntity() {
        //given
        var filialSmallResponses = List.of(new FilialSmallResponse(1L, "Юго Западный филиал", "Фурманова 125"),
                new FilialSmallResponse(2L, "Академический филиал", "Павла Шаманова 6"));
        Mockito.doReturn(filialSmallResponses).when(this.filialService).getAll();
        //when
        var responseEntity = this.filialController.getAllFilial();
        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filialSmallResponses, responseEntity.getBody());
    }
}
