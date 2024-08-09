package osteam.backland.global.Exception;

import lombok.Data;
import osteam.backland.global.Exception.model.CustomException;
import osteam.backland.global.Exception.model.ErrorCode;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int statusCode;
    private final String error;
    private final String message;

    public ExceptionResponse(CustomException ex) {
        this.statusCode = ex.getHttpStatus().value();
        this.error = ex.getHttpStatus().name();
        this.message = ex.getDetail();
    }

    public ExceptionResponse(ErrorCode errorCode, String message) {
        this.statusCode = errorCode.getHttpStatus().value();
        this.error = errorCode.getHttpStatus().name();
        this.message = message;
    }
}
