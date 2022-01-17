package bo.com.dt.challenge.service.dto.category;

import bo.com.dt.challenge.constants.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryQueryDto {
    long id;
    private String name;
    private String description;
    private State state;
}
