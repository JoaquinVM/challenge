package bo.com.dt.challenge.controller;

import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.service.OrderService;
import bo.com.dt.challenge.service.dto.order.OrderAddDto;
import bo.com.dt.challenge.service.dto.order.OrderQueryDto;
import bo.com.dt.challenge.service.dto.order.OrderUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Order Api Rest",description = "Endpoint para gestionar Ordenes")
@RestController
@RequestMapping("api/orders")
public class OrderController {
private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Crea una Orden nueva")
    @PostMapping(path = "")
    public ResponseEntity<OrderQueryDto> addOrder(@ApiParam(value = "Identificador del restaurante") @RequestParam long restaurantId,
                                                  @ApiParam(value = "Datos de una orden") @Valid @RequestBody OrderAddDto orderAddDto) throws BadRequestException,NotFoundException {
        return new ResponseEntity<>(orderService.addOrder(restaurantId, orderAddDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualzia una orden")
    @PutMapping(path = "{orderId}")
    public ResponseEntity<OrderQueryDto> updateOrder(@ApiParam(value = "Identificador de la orden") @PathVariable long orderId,
                                                     @ApiParam(value = "Datos de la orden") @Valid @RequestBody OrderUpdateDto orderUpdateDto) throws BadRequestException, NotFoundException {
        return new ResponseEntity<>(orderService.updateOrder(orderId, orderUpdateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina una orden")
    @DeleteMapping(path = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@ApiParam(value = "Identificador de ordenes") @PathVariable long orderId) throws BadRequestException, NotFoundException {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
