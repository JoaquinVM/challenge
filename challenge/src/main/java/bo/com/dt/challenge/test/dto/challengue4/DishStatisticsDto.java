package bo.com.dt.challenge.test.dto.challengue4;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DishStatisticsDto {
    private long id;
    private String name;
    private long sales;
    private BigDecimal averageSalesPerMonth;
    //Ordenar o por numero de ventas o promedio desc
}
