package bo.com.dt.challenge.service.impl;

import bo.com.dt.challenge.constants.Constants;
import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.exception.message.Message;
import bo.com.dt.challenge.exception.message.MessageError;
import bo.com.dt.challenge.model.entity.Dish;
import bo.com.dt.challenge.model.entity.QDish;
import bo.com.dt.challenge.model.repository.DishRepository;
import bo.com.dt.challenge.service.CategoryService;
import bo.com.dt.challenge.service.DishService;
import bo.com.dt.challenge.service.RestaurantService;
import bo.com.dt.challenge.service.dto.dish.DishAddDto;
import bo.com.dt.challenge.service.dto.dish.DishQueryDto;
import bo.com.dt.challenge.service.dto.dish.DishUpdateDto;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Dish getDishById(long pDishId) throws NotFoundException{
        Dish vDish = dishRepository.getDishById(pDishId).orElse(null);
        if (vDish == null) {
            Object[] vObj = {Constants.DISH, pDishId};
            throw new NotFoundException(MessageError.GetExceptionBasic(Message.NOT_FOUND, vObj), DishService.class);
        }
        return vDish;
    }

    @Override
    public List<DishQueryDto> getDishes(long pRestaurantId, State pState, String pFilter) {
        QDish vQDish = QDish.dish;
        BooleanBuilder vPredicate = new BooleanBuilder();
        vPredicate.and(vQDish.restaurant.id.eq(pRestaurantId))
                .and(vQDish.state.eq(pState));
        if (!Strings.isNullOrEmpty(pFilter)) {
            vPredicate.and(vQDish.name.containsIgnoreCase(pFilter)
                    .or(vQDish.description.containsIgnoreCase(pFilter)));
        }

        List<Dish> vDishes = Lists.newArrayList(dishRepository.findAll(vPredicate));
        List<DishQueryDto> vDishQueryDtoList = new ArrayList<>();
        for (Dish vDish : vDishes) {
            vDishQueryDtoList.add(this.generateDishQueryDto(vDish));
        }
        return vDishQueryDtoList;
    }

    @Override
    public DishQueryDto addDish(long pRestaurantId, DishAddDto pDishAddDto) throws NotFoundException, BadRequestException {
        if (dishRepository.isDishNameRepeated(pDishAddDto.getName())) {
            throw new BadRequestException(MessageError.GetExceptionBasic(Message.DISH_NAME_DUPLICATE, pDishAddDto.getName()), CategoryService.class);
        }
        Dish vDish = new Dish();
        BeanUtils.copyProperties(pDishAddDto, vDish);
        vDish.setRestaurant(restaurantService.getRestaurantById(pRestaurantId));
        dishRepository.save(vDish);
        return this.generateDishQueryDto(vDish);
    }

    @Override
    public DishQueryDto updateDish(long pDishId, DishUpdateDto pDishUpdateDto) throws NotFoundException, BadRequestException{
        Dish vDish = this.getDishById(pDishId);

        if (!vDish.getName().equals(pDishUpdateDto.getName()) && dishRepository.isDishNameRepeated(pDishUpdateDto.getName())) {
            throw new BadRequestException(MessageError.GetExceptionBasic(Message.DISH_NAME_DUPLICATE, pDishUpdateDto.getName()), DishService.class);
        }

        BeanUtils.copyProperties(pDishUpdateDto, vDish);
        dishRepository.save(vDish);
        return this.generateDishQueryDto(vDish);
    }

    @Override
    public void deleteDish(long pDishId) throws NotFoundException {
        Dish vDish = this.getDishById(pDishId);
        vDish.setDeletedDate(LocalDateTime.now());
        dishRepository.save(vDish);
    }

    private DishQueryDto generateDishQueryDto(Dish pDish) {
        DishQueryDto vDishQueryDto = new DishQueryDto();
        BeanUtils.copyProperties(pDish, vDishQueryDto);
        return vDishQueryDto;
    }


}
