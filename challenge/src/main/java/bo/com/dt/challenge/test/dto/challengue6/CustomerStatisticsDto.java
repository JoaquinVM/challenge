package bo.com.dt.challenge.test.dto.challengue6;

import bo.com.dt.challenge.test.dto.challenge2.DishBasicDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomerStatisticsDto {
    private String name;
    private DishBasicDto favoriteDish;
    private BigDecimal amountPaid;
    private long ordersQuantity;
    //Ordenar por montoPagado o por cantidad de ordenes desc
}
