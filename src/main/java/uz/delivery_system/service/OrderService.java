package uz.delivery_system.service;

import uz.delivery_system.dto.order.OrderProductsDTO;
import uz.delivery_system.dto.order.CreateOrderDTO;
import uz.delivery_system.dto.order.OrderDTO;

import java.util.List;

/**
 * Created by Nodirbek on 02.09.2017.
 */
public interface OrderService {

    void createOrder(CreateOrderDTO createOrderDTO);

    List<OrderDTO> listOrders();

    List<OrderProductsDTO> listOrderProducts(Long id);

    String changeStatus(Long id, short status);

    String acceptProduct(Long id, Long productId, Boolean accepted);

    String revertOrder(Long id);
}
