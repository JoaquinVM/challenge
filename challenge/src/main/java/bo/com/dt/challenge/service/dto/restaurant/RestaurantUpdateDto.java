package bo.com.dt.challenge.service.dto.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RestaurantUpdateDto {

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 30, message = "El nombre debe tener un largo entre 2 y 30")
    private String name;

    @NotNull(message = "La descripcion no pude ser nula")
    @Size(min = 2, max = 30, message = "La descripcion debe tener un largo entre 2 y 30")
    private String description;

    @NotNull(message = "El telefono no pude ser nulo")
    private String telephone;

    @NotNull(message = "La direccion no pude ser nula")
    @Size(min = 10, max = 300, message = "La direccion debe tener un largo entre 10 y 300")
    private String address;
}
