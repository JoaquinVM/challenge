package bo.com.dt.challenge.controller;

import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantAddDto;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantQueryDto;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantUpdateDto;
import bo.com.dt.challenge.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Restaurant Api Rest", description = "EndPoint para gestionar restaurantes")
@RestController
@RequestMapping("api/restaurantes")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @ApiOperation(value = "Crea un restaurante")
    @PostMapping(path ="")
    public ResponseEntity<RestaurantQueryDto> addRestaurant(@ApiParam(value = "Datos de restaurante") @Valid @RequestBody RestaurantAddDto restaurantAddDto) throws BadRequestException {
        return new ResponseEntity<>(restaurantService.addRestaurant(restaurantAddDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza un restaurante")
    @PutMapping(path ="{restaurantId}")
    public ResponseEntity<RestaurantQueryDto> updateRestaurant(@ApiParam(value="Identificador de restaurante") @PathVariable long restaurantId,
                                                               @ApiParam(value = "Datos de restaurante") @Valid @RequestBody RestaurantUpdateDto restaurantUpdateDto) throws BadRequestException, NotFoundException {
        return new ResponseEntity<>(restaurantService.updateRestaurant(restaurantId, restaurantUpdateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina un restaurante")
    @DeleteMapping(path ="{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@ApiParam(value = "Identificador de restaurante") @PathVariable long restaurantId) throws BadRequestException, NotFoundException{
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
