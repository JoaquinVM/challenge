package bo.com.dt.challenge.service.impl;

import bo.com.dt.challenge.constants.Constants;
import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.exception.message.Message;
import bo.com.dt.challenge.exception.message.MessageError;
import bo.com.dt.challenge.model.entity.Restaurant;
import bo.com.dt.challenge.model.repository.RestaurantRepository;
import bo.com.dt.challenge.service.RestaurantService;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantAddDto;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantQueryDto;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantUpdateDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant getRestaurantById(long pRestaurantId) throws NotFoundException {
        Restaurant vRestaurant = restaurantRepository.getRestaurantById(pRestaurantId).orElse(null);
        if(vRestaurant == null){
            Object[] vObj = {Constants.RESTAURANT, pRestaurantId};
            throw new NotFoundException(MessageError.GetExceptionBasic(Message.NOT_FOUND, vObj), RestaurantService.class);
        }
        return vRestaurant;
    }

    @Override
    public List<RestaurantQueryDto> getRestaurants(State state) {
        return null;
    }

    @Override
    public RestaurantQueryDto addRestaurant(RestaurantAddDto pRestaurantAddDto) throws BadRequestException {
        if(restaurantRepository.isRestaurantNameRepeated(pRestaurantAddDto.getName())){
            throw new BadRequestException(MessageError.GetExceptionBasic(Message.RESTAURANT_NAME_DUPLICATE, pRestaurantAddDto.getName()), RestaurantService.class);
        }

        Restaurant vRestaurant = new Restaurant();
        BeanUtils.copyProperties(pRestaurantAddDto, vRestaurant);
        restaurantRepository.save(vRestaurant);
        return this.generateRestaurantQueryDto(vRestaurant);
    }

    @Override
    public RestaurantQueryDto updateRestaurant(long pRestaurantId, RestaurantUpdateDto pRestaurantUpdateDto) throws NotFoundException, BadRequestException {
        Restaurant vRestaurant = this.getRestaurantById(pRestaurantId);

        if(!vRestaurant.getName().equals(pRestaurantUpdateDto.getName()) && restaurantRepository.isRestaurantNameRepeated(pRestaurantUpdateDto.getName())){
            throw new BadRequestException(MessageError.GetExceptionBasic(Message.RESTAURANT_NAME_DUPLICATE, pRestaurantUpdateDto.getName()), RestaurantService.class);
        }

        BeanUtils.copyProperties(pRestaurantUpdateDto, vRestaurant);
        restaurantRepository.save(vRestaurant);
        return this.generateRestaurantQueryDto(vRestaurant);
    }

    @Override
    public void deleteRestaurant(long pRestaurantId) throws NotFoundException{
        Restaurant vRestaurant = this.getRestaurantById(pRestaurantId);
        vRestaurant.setDeletedDate(LocalDateTime.now());
        restaurantRepository.save(vRestaurant);
    }

    private RestaurantQueryDto generateRestaurantQueryDto(Restaurant pRestaurant){
        RestaurantQueryDto vRestaurantQueryDto = new RestaurantQueryDto();
        BeanUtils.copyProperties(pRestaurant, vRestaurantQueryDto);
        return vRestaurantQueryDto;
    }
}
