package ru.the_boring_developers.common.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}

