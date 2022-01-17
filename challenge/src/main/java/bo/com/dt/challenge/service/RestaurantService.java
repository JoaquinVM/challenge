package bo.com.dt.challenge.service;

import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.model.entity.Restaurant;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantAddDto;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantQueryDto;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantUpdateDto;

import java.util.List;

//Nos dice que metodos va a tener nuestro servicio.
//Se crea una interface y tambien una clase para facilitar el manejo de los metodos disponibles para cada entidad
public interface RestaurantService {
    Restaurant getRestaurantById(long pRestaurantId) throws NotFoundException;

    List<RestaurantQueryDto> getRestaurants(State state);

    RestaurantQueryDto addRestaurant(RestaurantAddDto pRestaurantAddDto) throws BadRequestException;

    RestaurantQueryDto updateRestaurant(long pRestaurantId, RestaurantUpdateDto pRestaurantUpdateDto) throws NotFoundException, BadRequestException;

    void deleteRestaurant(long pRestaurantId) throws NotFoundException;
}