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
@Table(name = "res_platos")
public class Dish extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurant restaurant;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
    @Column(name = "nombre")
    private String name;

    @NotNull
    @Column(name = "descripcion")
    private String description;

    @NotNull(message = "El nombre no puede ser nulo")
    @Column(name = "precio")
    private BigDecimal price;

    @ManyToMany(mappedBy = "dishes")
    private List<Category> categories;
}
