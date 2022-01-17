package bo.com.dt.challenge.service.dto.order;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderAddDto {
        @NotNull(message = "El nombre no puede ser nulo")
        @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
        private String customerName;

        @NotNull
        @Column(name = "numero_mesa")
        private long tableNumber;

        @NotNull(message = "El precio no puede ser Nulo")
        @Digits(integer = 2, fraction = 12, message = "El Precio tien que tener 12 decimales ")
        private BigDecimal totalCost;

        private List<OrderDishAddDto> orderDishes;
}
