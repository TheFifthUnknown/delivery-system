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

/**
 * Created by Nodirbek on 02.09.2017.
 */
@Api(description = "Buyurtmalar")
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Buyurma berish/yaratish", notes = "Yangi buyurtma berish")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated CreateOrderDTO createOrderDTO){
        orderService.createOrder(createOrderDTO);
        return ResponseEntity.ok("Buyurtma qabul qilindi");
    }

    @ApiOperation(value = "Buyurmalar ro'yhati", notes = "Buyurmalar ro'yhatini ko'rish")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<OrderDTO>> listOrders(){
        List<OrderDTO> list = orderService.listOrders();
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "buyurtma maxsulotlari", notes = "Berilgan {id} li buyurtma va uni ichidagi maxsulotlarni ko'rish")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<OrderProductsDTO>> listOrderProducts(@PathVariable Long id){
        List<OrderProductsDTO> list = orderService.listOrderProducts(id);
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "buyurtma statusi", notes = "Berilgan {id} li buyurtmani statusini o'zgartirish. Buyurtma statuslari: <br/>" +
            "0 -> buyurtma kutish holatida <br/>" +
            "1 -> buyurtma bekor qilindi <br/>" +
            "2 -> buyurtma qabul qilindi <br/>" +
            "3 -> buyurtma yetkazib berildi <br/>" +
            "0 statusidan 1 yoki 2 ga o'tish mumkin. 2 statusidan faqat 3 ga o'tish mumkin.")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/status/{status}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> changeStatus(@PathVariable Long id, @PathVariable short status){
        String message = orderService.changeStatus(id, status);
        return ResponseEntity.ok(message);
    }

    @ApiOperation(value = "buyurtma maxsulotlarini qabul qilish", notes = "berilgan {id} li buyurtmani ichidagi {productId} li mahsulotni" +
            " qabul qilish yoki shu maxsulotga buyurtmani bekor qilish. Bu yerda {accepted} parametri sifatida true yoki false qiymat yuboriladi")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/product/{productId}/accepted/{accepted}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> changeStatus(@PathVariable Long id, @PathVariable Long productId, @PathVariable Boolean accepted){
        String message = orderService.acceptProduct(id, productId, accepted);
        return ResponseEntity.ok(message);
    }

    @ApiOperation(value = "Buyurtmani qaytarib olish", notes = "Buyurtmani qaytarib olish. Agarda buyurtma qabul qilingan bo'lsa, " +
            "lekin xaridor tomonidan bu buyurtma qaytarib yuborilsa, buyurtma shu method orqali bekor qilindi. Bu yerda {id} buyurtma idsi")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/revert", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> revertOrder(@PathVariable Long id){
        String message = orderService.revertOrder(id);
        return ResponseEntity.ok(message);
    }
}
