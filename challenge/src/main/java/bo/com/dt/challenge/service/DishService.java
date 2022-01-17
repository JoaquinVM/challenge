package bo.com.dt.challenge.service;

import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.model.entity.Dish;
import bo.com.dt.challenge.service.dto.dish.DishAddDto;
import bo.com.dt.challenge.service.dto.dish.DishQueryDto;
import bo.com.dt.challenge.service.dto.dish.DishUpdateDto;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantAddDto;

import java.util.List;

public interface DishService {
    Dish getDishById(long pDishId) throws NotFoundException;

    List<DishQueryDto> getDishes(long pRestaurantId, State pState, String pFilter);

    DishQueryDto addDish(long pRestaurantId, DishAddDto pDishAddDto) throws NotFoundException, BadRequestException;

    DishQueryDto updateDish(long pDishId, DishUpdateDto pDishUpdateDto) throws NotFoundException, BadRequestException;

    void deleteDish(long pDishId) throws NotFoundException;
}
