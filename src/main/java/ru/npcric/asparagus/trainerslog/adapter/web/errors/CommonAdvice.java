package ru.npcric.asparagus.trainerslog.adapter.web.errors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommonAdvice extends ResponseEntityExceptionHandler {
    static String VALIDATION_ERROR = "validation_error";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors().stream().map(fieldError ->
                        new ErrorResponse(VALIDATION_ERROR, fieldError.getField(), fieldError.getDefaultMessage())).
                toList();

        List<ErrorResponse> result = ex.getBindingResult().
                getGlobalErrors().
                stream().map(e -> new ErrorResponse(VALIDATION_ERROR, e.getDefaultMessage()))
                .toList();

        errors.addAll(result);
        return ResponseEntity.badRequest()
                .body(errors);
    }
}
