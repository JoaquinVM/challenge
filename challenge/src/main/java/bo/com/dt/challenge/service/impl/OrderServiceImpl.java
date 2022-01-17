package bo.com.dt.challenge.service.impl;

import bo.com.dt.challenge.constants.Constants;
import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.exception.message.Message;
import bo.com.dt.challenge.exception.message.MessageError;
import bo.com.dt.challenge.model.entity.Order;
import bo.com.dt.challenge.model.repository.OrderRepository;
import bo.com.dt.challenge.service.OrderService;
import bo.com.dt.challenge.service.RestaurantService;
import bo.com.dt.challenge.service.dto.order.OrderAddDto;
import bo.com.dt.challenge.service.dto.order.OrderDishAddDto;
import bo.com.dt.challenge.service.dto.order.OrderQueryDto;
import bo.com.dt.challenge.service.dto.order.OrderUpdateDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService  {

    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;


    public OrderServiceImpl(OrderRepository orderRepository,RestaurantService restaurantService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
    }

    @Override
    public Order getOrderById(long pOrderId) throws NotFoundException {
        Order vOrder = orderRepository.getOrdersById(pOrderId).orElse(null);
        if(vOrder == null)
        {
            Object[] vObj = {Constants.ORDER,pOrderId};
            throw new NotFoundException(MessageError.GetExceptionBasic(Message.NOT_FOUND,vObj),OrderService.class);
        }
        return vOrder;
    }

    @Override
    public OrderQueryDto addOrder(long pRestaurantId ,OrderAddDto pOrderAddDto) throws NotFoundException,BadRequestException {
        Order vOrder = new Order();
        BeanUtils.copyProperties(pOrderAddDto, vOrder);
        vOrder.setRestaurant(restaurantService.getRestaurantById(pRestaurantId));
        if(vOrder.getTotalCost()==null||
           vOrder.getTotalCost().equals(BigDecimal.ZERO))
        {
            throw new BadRequestException(MessageError.GetExceptionBasic(Message.ORDER_ZERO), OrderService.class);
        }
        this.addDishesToOrder(vOrder, pOrderAddDto.getOrderDishes());
        orderRepository.save(vOrder);
        return this.generateOrderQueryDto(vOrder);
    }

    @Override
    public OrderQueryDto updateOrder(long pOrderId, OrderUpdateDto pOrderUpdateDto) throws NotFoundException, BadRequestException {
        if(pOrderUpdateDto.getTotalCost()== null || pOrderUpdateDto.getTotalCost().equals(BigDecimal.ZERO))
        {
            throw new BadRequestException(MessageError.GetExceptionBasic(Message.ORDER_ZERO), OrderService.class);
        }
        Order vOrder = this.getOrderById(pOrderId);
        BeanUtils.copyProperties(pOrderUpdateDto, vOrder);
        orderRepository.save(vOrder);
        return this.generateOrderQueryDto(vOrder);

    }

    @Override
    public void deleteOrder(long pOrderId) throws NotFoundException {
        Order vOrder = this.getOrderById(pOrderId);
        vOrder.setDeletedDate(LocalDateTime.now());
        orderRepository.save(vOrder);
    }

    private void addDishesToOrder(Order pOrder, List<OrderDishAddDto> orderDishes) {
        //Validaciones
        //Validaciones subtotales
        //Validaciones total
        //Crear Order Dish, asignadonle todos sus campos
        //Add Order
    }

    @Override
    public OrderQueryDto generateOrderQueryDto(Order pOrder) {
        OrderQueryDto vOrderQueryDto = new OrderQueryDto();
        BeanUtils.copyProperties(pOrder, vOrderQueryDto);
        return vOrderQueryDto;
    }
}
