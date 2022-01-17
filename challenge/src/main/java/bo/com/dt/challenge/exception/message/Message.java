package bo.com.dt.challenge.exception.message;

import lombok.Getter;

@Getter
public enum Message {
    TEST("ERR-001", "El restaurante %s no esta habilitado porque su direccion %s es muy larga"),
    NOT_FOUND("ERR-002", "No se encontró %s con el id %s"),
    CATEGORY_NAME_DUPLICATE("ERR-003", "Ya existe una categoria con el nombre %s"),
    RESTAURANT_NAME_DUPLICATE("ERR-004","Ya existe un restaurante con el nombre %s"),
    DISH_NAME_DUPLICATE("ERR-003", "Ya existe un plato con el nombre %s"),
    ORDER_ZERO("ERR-004","La orden contiene un precio invalido");


    private String code;
    private String message;

    Message(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
