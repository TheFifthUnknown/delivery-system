package uz.delivery_system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.delivery_system.dto.order.OrderProductsDTO;
import uz.delivery_system.dto.order.CreateOrderDTO;
import uz.delivery_system.dto.order.OrderDTO;
import uz.delivery_system.service.OrderService;

import java.util.List;


@Api(description = "Orders")
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Create/issue an order", notes = "Новый заказ")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated CreateOrderDTO createOrderDTO){
        orderService.createOrder(createOrderDTO);
        return ResponseEntity.ok("Order accepted");
    }

    @ApiOperation(value = "List of orders", notes = "Просмотр списка заказов")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<OrderDTO>> listOrders(){
        List<OrderDTO> list = orderService.listOrders();
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "order products", notes = "Заданный {id} заказ и просмотр продуктов в нем")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<OrderProductsDTO>> listOrderProducts(@PathVariable Long id){
        List<OrderProductsDTO> list = orderService.listOrderProducts(id);
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "order status", notes = "Изменить статус заказа на данный {id}. Статус заказа: <br/>" +
            "0 -> заказ находится в режиме ожидания <br/>" +
            "1 -> заказ отменен <br/>" +
            "2 -> Заказ принят <br/>" +
            "3 -> заказ доставлен <br/>" +
            "0 статус может быть изменен на 1 или 2. Из статуса 2 можно перейти только в статус 3.")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/status/{status}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> changeStatus(@PathVariable Long id, @PathVariable short status){
        String message = orderService.changeStatus(id, status);
        return ResponseEntity.ok(message);
    }

    @ApiOperation(value = "order products acceptance", notes = "дано {id} внутри заказа {productId} продукта" +
            "Акцепт или отмена заказа на тот же товар. Здесь в качестве параметра {accepted} передается значение true или false")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/product/{productId}/accepted/{accepted}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> changeStatus(@PathVariable Long id, @PathVariable Long productId, @PathVariable Boolean accepted){
        String message = orderService.acceptProduct(id, productId, accepted);
        return ResponseEntity.ok(message);
    }

    @ApiOperation(value = "Return of order", notes = "Возврат заказа. Если заказ принят, " +
            "но если этот заказ был отправлен обратно покупателем, заказ был отменен с помощью этого метода. Здесь {id} идентификатор заказа")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/revert", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> revertOrder(@PathVariable Long id){
        String message = orderService.revertOrder(id);
        return ResponseEntity.ok(message);
    }
}
