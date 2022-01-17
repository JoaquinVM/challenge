package bo.com.dt.challenge.test.dto.challenge1;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryAssignedDishesDto {
    private long id;
    private String name;
    private List<DishAssignedDto> assignedDishes;
}
