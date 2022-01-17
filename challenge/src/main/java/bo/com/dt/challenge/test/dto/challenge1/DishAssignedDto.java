package bo.com.dt.challenge.test.dto.challenge1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishAssignedDto {
    private long id;
    private String dishName;
    private boolean assigned;
}
