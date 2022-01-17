package bo.com.dt.challenge.service.dto.order;


import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
@Getter
@Setter
public class OrderQueryDto {

    private String customerName;
    private long tableNumber;
    private BigDecimal totalCost;
}