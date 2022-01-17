package bo.com.dt.challenge.service.dto.dish;

import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.model.entity.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DishQueryDto {
    private String name;
    private String description;
    private BigDecimal price;
    private State state;
}
