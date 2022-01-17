package bo.com.dt.challenge.exception.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiErrorDto {
    private String code;
    private String message;
    private List<String> subErrors;
    private String detail;

    public ApiErrorDto(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public ApiErrorDto(String code, String message, String detail, List<String> subErrors) {
        this.code = code;
        this.message = message;
        this.subErrors = subErrors;
        this.detail = detail;
    }
}
