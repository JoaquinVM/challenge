package bo.com.dt.challenge.service.dto.category;

import bo.com.dt.challenge.constants.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryAddDto {
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
    @Column(name = "nombre")
    private String name;

    @NotNull(message = "La descripcion no puede ser nula")
    @Size(min = 2, max = 30, message = "La descripción debe tener un largo entre 2 y 30")
    @Column(name = "descripcion")
    private String description;

}
