package bo.com.dt.challenge.controller;

import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.service.DishService;
import bo.com.dt.challenge.service.dto.dish.DishAddDto;
import bo.com.dt.challenge.service.dto.dish.DishQueryDto;
import bo.com.dt.challenge.service.dto.dish.DishUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Dish Api Rest", description = "Endpoint para gestionar platos")
@RestController
@RequestMapping("api/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @ApiOperation(value = "Obtiene un listado de platos")
    @GetMapping(path = "")
    public ResponseEntity<List<DishQueryDto>> getDishes(@ApiParam(value = "Identificador del restaurante") @RequestParam long restaurantId,
                                                        @ApiParam(value = "Estado") @RequestParam State state,
                                                        @ApiParam(value = "Filtro") @RequestParam(required = false) String filter) throws NotFoundException, BadRequestException {
        return new ResponseEntity<>(dishService.getDishes(restaurantId, state, filter), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Crea una plato")
    @PostMapping(path = "")
    public ResponseEntity<DishQueryDto> addDish(@ApiParam(value = "Identificador del restaurante") @RequestParam long restaurantId,
                                                @ApiParam(value = "Datos del Plato") @Valid @RequestBody DishAddDto dishAddDto) throws NotFoundException, BadRequestException {
        return new ResponseEntity<>(dishService.addDish(restaurantId, dishAddDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza un plato")
    @PutMapping(path = "{dishId}")
    public ResponseEntity<DishQueryDto> updateDish(@ApiParam(value = "Identificador de plato") @PathVariable long dishId,
                                                   @ApiParam(value = "Datos de plato") @Valid @RequestBody DishUpdateDto dishUpdateDto) throws BadRequestException, NotFoundException {
        return new ResponseEntity<>(dishService.updateDish(dishId, dishUpdateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina una plato")
    @DeleteMapping(path = "{dishId}")
    public ResponseEntity<Void> deleteDish(@ApiParam(value = "Identificador de plato") @PathVariable long dishId) throws BadRequestException, NotFoundException {
        dishService.deleteDish(dishId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
