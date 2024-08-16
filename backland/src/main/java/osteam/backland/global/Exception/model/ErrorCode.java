package osteam.backland.global.Exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    OK(HttpStatus.OK),
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND),
    NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;
}
