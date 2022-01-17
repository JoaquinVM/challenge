package bo.com.dt.challenge.exception.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExceptionBasicDto {
    private String code;
    private String message;

    public ExceptionBasicDto(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ExceptionBasicDto() {}
}
