package osteam.backland.global.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import osteam.backland.global.Exception.model.CustomException;
import osteam.backland.global.Exception.model.ErrorCode;

@Slf4j
@RestControllerAdvice // 전역 예외 처리
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ExceptionResponse> handleCustomException(CustomException e){
        return createExceptionResponse(e);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        logFiledFromBindingResults(e.getBindingResult());
        return createExceptionResponse(ErrorCode.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<Object> createExceptionResponse(ErrorCode errorCode, String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorCode, message);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    private ResponseEntity<ExceptionResponse> createExceptionResponse(CustomException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
    private void logFiledFromBindingResults(BindingResult bindingResult) {
        bindingResult.getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;

            String fieldName = fieldError.getField();       // 이름
            String message = fieldError.getDefaultMessage();  // 에러 메세지
            Object value = fieldError.getRejectedValue(); // 주입된 실제 값. 널일 수 있어서 toString() 호출하기 애매함.


            log.error("fieldName : {}", fieldName);
            log.error("message : {}", message);
            log.error("value : {}", value);
        });
    }
}
