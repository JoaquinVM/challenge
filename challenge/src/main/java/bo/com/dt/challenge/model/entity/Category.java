package bo.com.dt.challenge.model.entity;

import bo.com.dt.challenge.constants.State;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "res_categorias")
public class Category extends BaseEntity {
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "res_platos_categorias",
            joinColumns = @JoinColumn(name = "id_categoria", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_plato", referencedColumnName = "id"))
    private List<Dish> dishes;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
    @Column(name = "nombre")
    private String name;

    @NotNull(message = "La descripcion no puede ser nula")
    @Size(min = 2, max = 30, message = "La descripción debe tener un largo entre 2 y 30")
    @Column(name = "descripcion")
    private String description;
}
