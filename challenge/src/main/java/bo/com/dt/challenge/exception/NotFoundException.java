package bo.com.dt.challenge.exception;

import bo.com.dt.challenge.exception.dto.ExceptionBasicDto;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class NotFoundException extends Exception {
    public String detail;
    public String code;

    public NotFoundException(ExceptionBasicDto exceptionBasicDto, Class clazz) {
        super(exceptionBasicDto.getMessage());
        this.detail = StringUtils.capitalize(clazz.getSimpleName());
        this.code = exceptionBasicDto.getCode();
    }
}
