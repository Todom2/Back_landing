package osteam.backland.global.Exception.model;

import lombok.Data;

@Data
public class CommonExceptionResponse {
    private final String code;
    private final String message;
}
