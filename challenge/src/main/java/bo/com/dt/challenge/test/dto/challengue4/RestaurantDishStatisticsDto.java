package bo.com.dt.challenge.test.dto.challengue4;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDishStatisticsDto {
    private long id;
    private String name;
    private List<DishStatisticsDto> dishStatistics;
}
