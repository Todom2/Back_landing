package osteam.backland.global.Exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String detail;

    public CustomException(ErrorCode errorCode, String subject){
        super(subject);

        this.httpStatus = errorCode.getHttpStatus();
        this.detail = subject;
    }
}
