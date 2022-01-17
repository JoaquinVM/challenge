package bo.com.dt.challenge.test.dto.challengue5;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryRestaurantStatisticDto {
    private long id;
    private String name;
    private List<RestaurantStatisticsDto> restaurantStatistics;
}
