package bo.com.dt.challenge.service;

import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.model.entity.Order;
import bo.com.dt.challenge.service.dto.order.OrderAddDto;
import bo.com.dt.challenge.service.dto.order.OrderQueryDto;
import bo.com.dt.challenge.service.dto.order.OrderUpdateDto;

public interface OrderService {
    Order getOrderById(long pOrderId) throws NotFoundException;
    OrderQueryDto addOrder(long pRestaurantId, OrderAddDto pOrderAddDto) throws NotFoundException, BadRequestException;;
    OrderQueryDto updateOrder(long pOrderId, OrderUpdateDto pCategoryUpdateDto) throws NotFoundException, BadRequestException;
    void deleteOrder(long pOrderId) throws NotFoundException;
    OrderQueryDto generateOrderQueryDto(Order pOrder);
}