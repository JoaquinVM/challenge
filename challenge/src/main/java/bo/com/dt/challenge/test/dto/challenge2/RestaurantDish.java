package bo.com.dt.challenge.test.dto.challenge2;

import bo.com.dt.challenge.service.dto.dish.DishQueryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDish {
    private long id;
    private String name;
    private List<DishBasicDto> dishes;
}
