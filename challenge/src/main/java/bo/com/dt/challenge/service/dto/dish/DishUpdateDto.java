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
public class DishUpdateDto {
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
    private String name;

    @NotNull(message = "Se necesita una descripcion del plato")
    private String description;

    @NotNull(message = "El precio no puede ser nulo")
    private BigDecimal price;



}
