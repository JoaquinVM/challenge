package bo.com.dt.challenge.test.dto.challengue3;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDishesDto {
    private long id;
    private String name;
    private List<String> dishesNames;
}
