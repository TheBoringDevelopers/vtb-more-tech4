package ru.the_boring_developers.api.controller.advice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.the_boring_developers.common.exception.DomainException;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class ErrorControllerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorEntity> userError(DomainException e) {
        return ResponseEntity.badRequest().body(new ErrorEntity(e.getMessage()));
    }
}
