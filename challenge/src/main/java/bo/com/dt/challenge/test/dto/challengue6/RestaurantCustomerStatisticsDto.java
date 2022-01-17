package bo.com.dt.challenge.test.dto.challengue6;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantCustomerStatisticsDto {
    private long id;
    private long name;
    private List<CustomerStatisticsDto> customerStatistics;
}
