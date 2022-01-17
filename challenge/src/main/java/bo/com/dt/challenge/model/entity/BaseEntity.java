package bo.com.dt.challenge.model.entity;

import bo.com.dt.challenge.constants.State;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Identificador generado por la base de datos")
    private Long id;

    @NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private State state;

    @NotNull(message = "La fecha de creación no puede ser nula")
    @Column(name = "fecha_alta")
    private LocalDateTime createdDate;

    @Column(name = "fecha_baja")
    private LocalDateTime deletedDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
        state = State.ACTIVE;
    }

    @PreUpdate
    public void preUpdate() {
        if (deletedDate != null) {
            state = State.DELETED;
        }
    }
}
