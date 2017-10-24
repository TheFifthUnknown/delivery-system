package uz.delivery_system.controller;

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

/**
 * Created by Nodirbek on 02.09.2017.
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity create(@RequestBody @Validated CreateOrderDTO createOrderDTO){
        orderService.createOrder(createOrderDTO);
        return ResponseEntity.ok("Buyurtma qabul qilindi");
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity listOrders(){
        List<OrderDTO> list = orderService.listOrders();
        return ResponseEntity.ok(list);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity listOrderProducts(@PathVariable Long id){
        List<OrderProductsDTO> list = orderService.listOrderProducts(id);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/status/{status}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity changeStatus(@PathVariable Long id, @PathVariable short status){
        String message = orderService.changeStatus(id, status);
        return ResponseEntity.ok(message);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/product/{productId}/accepted/{accepted}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity changeStatus(@PathVariable Long id, @PathVariable Long productId, @PathVariable Boolean accepted){
        String message = orderService.acceptProduct(id, productId, accepted);
        return ResponseEntity.ok(message);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/revert", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity revertOrder(@PathVariable Long id){
        String message = orderService.revertOrder(id);
        return ResponseEntity.ok(message);
    }
}
