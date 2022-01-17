package bo.com.dt.challenge.test.dto.challenge2;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryRestaurantDto {
    private long id;
    private String name;
    private List<RestaurantDish> restaurantDishes;
}
