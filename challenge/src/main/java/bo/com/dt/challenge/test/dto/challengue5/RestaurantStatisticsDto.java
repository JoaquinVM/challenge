package bo.com.dt.challenge.test.dto.challengue5;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantStatisticsDto {
    private long id;
    private String name;
    private long sales;
    private long dishNumber;
    private long averageDishPrice;
    //Ordenar por numerov entas o por promedio desc
}
