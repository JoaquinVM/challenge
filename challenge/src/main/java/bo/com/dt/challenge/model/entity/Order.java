package bo.com.dt.challenge.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "res_ordenes")
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = "nombre_cliente")
    private String customerName;

    @NotNull
    @Column(name = "numero_mesa")
    private String tableNumber;

    @NotNull
    @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
    @Column(name = "precio_total")
    private BigDecimal totalCost;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDish> orderDishes;
}
