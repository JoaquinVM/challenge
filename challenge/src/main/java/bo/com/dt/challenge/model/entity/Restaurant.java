package bo.com.dt.challenge.model.entity;

import bo.com.dt.challenge.constants.State;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "res_restaurantes")
public class Restaurant extends BaseEntity {
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
    @Column(name = "nombre")
    private String name;

    @NotNull(message = "La descripcion no pude ser nula")
    @Size(min = 2, max = 30, message = "La descripcion debe tener un largo entre 2 y 30")
    @Column(name = "descripcion")
    private String description;

    @NotNull(message = "El telefono no pude ser nulo")
    @Column(name = "telefono")
    private String telephone;

    @NotNull(message = "La direccion no pude ser nula")
    @Size(min = 10, max = 300, message = "La direccion debe tener un largo entre 10 y 300")
    @Column(name = "direccion")
    private String address;
}
