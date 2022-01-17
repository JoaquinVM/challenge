package bo.com.dt.challenge.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "res_ordenes_platos")
public class OrderDish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_plato", nullable = false)
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private Order order;

    @NotNull
    @Column(name = "cantidad")
    private int amount;

    @NotNull
    @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
    @Column(name = "subtotak")
    private BigDecimal subtotal;
}
