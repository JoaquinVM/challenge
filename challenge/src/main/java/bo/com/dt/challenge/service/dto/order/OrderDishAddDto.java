package bo.com.dt.challenge.service.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderDishAddDto {
    long dishId;

    @NotNull
    private int amount;

    @NotNull
    @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
    private BigDecimal subtotal;
}
