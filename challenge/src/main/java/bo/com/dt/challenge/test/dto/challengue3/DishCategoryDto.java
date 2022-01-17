package bo.com.dt.challenge.test.dto.challengue3;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishCategoryDto {
    private long id;
    private String name;
    private List<CategoryDishesDto> categoryDishes;
}
