package uz.delivery_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.delivery_system.dto.order.OrderProductsDTO;
import uz.delivery_system.dto.order.CreateOrderDTO;
import uz.delivery_system.dto.order.OrderDTO;
import uz.delivery_system.dto.order.ProductCount;
import uz.delivery_system.entity.*;
import uz.delivery_system.enums.UserRole;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.repository.*;
import uz.delivery_system.service.OrderService;
import uz.delivery_system.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nodirbek on 02.09.2017.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    @Autowired
    private FirmRepository firmRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void createOrder(CreateOrderDTO dto) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        ShopEntity shopEntity = userEntity.getShop();
        if (shopEntity == null){
            throw new NotFoundException(1, "Do'kon foydalanuvchisi logini bilan kiring");
        }
        FirmEntity firmEntity = firmRepository.findOne(dto.getFirmId());
        if (firmEntity == null) {
            throw new NotFoundException(1,"Bunday firma mavjud emas!");
        }
        OrderEntity orderEntity = new OrderEntity();
        List<OrderProductEntity> orderProductEntities = new ArrayList<>();
        int totalSum = 0;
        for(ProductCount productCount : dto.getProductCounts()) {
            OrderProductEntity orderProductEntity = new OrderProductEntity();
            ProductEntity productEntity = productRepository.findOne(productCount.getProductId());
            if (productEntity != null) {
                orderProductEntity.setProduct(productEntity);
                orderProductEntity.setAccepted(Boolean.TRUE);
                orderProductEntity.setCountProduct(productCount.getCount());
                orderProductEntity.setPriceProduct(productCount.getPrice());
                orderProductEntity.setOrder(orderEntity);
                totalSum += productCount.getCount() * productCount.getPrice();
            }
            orderProductEntities.add(orderProductEntity);
        }
        // Set Order fields
        orderEntity.setFirmEntity(firmEntity);
        orderEntity.setShopEntity(shopEntity);
        orderEntity.setOrderedDate(new Date());
        orderEntity.setOrderProducts(orderProductEntities);
        orderEntity.setStatus((short)0);
        orderEntity.setRegisterNumber(firmEntity.getShopContractNumber()+1);
        orderEntity.setOrderedProductsCount(dto.getProductCounts().size());
        orderEntity.setPaymentType(dto.getPaymentType());
        orderEntity.setOrderedProductsCost(totalSum);

        firmEntity.setShopContractNumber(firmEntity.getShopContractNumber()+1);
        firmRepository.save(firmEntity);
        //shopRepository.save(shopEntity);
        orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderDTO> listOrders() {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        List<OrderEntity> orderEntities;
        if(userEntity.getUserRole().equals(UserRole.SHOP_MANAGER)){
            ShopEntity shopEntity = userEntity.getShop();
            orderEntities = orderRepository.findByShopEntityIdOrderByIdDesc(shopEntity.getId());
        }else if(userEntity.getUserRole().equals(UserRole.FIRM_ADMIN)){
            FirmEntity firmEntity = userEntity.getFirm();
            orderEntities = orderRepository.findByFirmEntityIdOrderByIdDesc(firmEntity.getId());
        }else {
            throw new NotFoundException(1,"Do'kon yoki firma logini bilan kiring!");
        }
        List<OrderDTO> list = new ArrayList<>();
        orderEntities.forEach(orderEntity -> {
            list.add(fetchOrderDto(orderEntity));
        });
        return list;
    }

    @Override
    public List<OrderProductsDTO> listOrderProducts(Long id) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        OrderEntity orderEntity = null;
        if(userEntity.getUserRole().equals(UserRole.SHOP_MANAGER)){
            Long shopId = userEntity.getShop().getId();
            orderEntity = orderRepository.findByIdAndShopEntityId(id, shopId);
        }else if(userEntity.getUserRole().equals(UserRole.FIRM_ADMIN)){
            Long firmId = userEntity.getFirm().getId();
            orderEntity = orderRepository.findByIdAndFirmEntityId(id, firmId);
        }
        if (orderEntity == null) {
            throw new NotFoundException(1, "Bunday buyurtma topilmadi");
        }
        List<OrderProductsDTO> list = new ArrayList<>();
        orderEntity.getOrderProducts().forEach(orderProductEntity -> {
            list.add(fetchOrderProducts(orderProductEntity));
        });
        return list;
    }

    @Override
    public String changeStatus(Long id, short status) {
        OrderEntity orderEntity = orderRepository.findOne(id);
        short currentStatus = orderEntity.getStatus();
        String message = currentStatus != status ? "Buyurtmani bu statusga o'tkazib bo'lmaydi!":
                "Buyurtma avvaldan shu statusda turibdi!";
        if(currentStatus == 0){
            if(status == 1){
                message = "Buyurtma bekor qilindi!";
                orderEntity.setStatus(status);
            }
            if(status == 2){
                message = "Buyurtma qabul qilindi!";
                orderEntity.setStatus(status);
            }
        }else if(currentStatus == 2){
            if(status == 3){
                message = "Buyurtma yetkazilganligi tasdiqlandi!";
                orderEntity.setStatus(status);
                orderEntity.setDeliverDate(new Date());
            }
        }
        orderRepository.save(orderEntity);
        return message;
    }

    @Override
    public String acceptProduct(Long id, Long productId, Boolean accepted) {
        OrderProductEntity orderProductEntity =
                orderProductsRepository.findByOrderIdAndProductId(id,productId);
        if (orderProductEntity == null) {
            throw new NotFoundException(1, "Bu buyurtmada ko'rsatildan maxsulot topilmadi");
        }
        orderProductEntity.setAccepted(accepted);
        orderProductsRepository.save(orderProductEntity);
        String message = "Maxsulot buyutma"+(accepted ? "ga yetkazib berish uchun qo'shildi" :
                " maxsulotlari ro'yhatidan olib tashlandi");
        return message;
    }

    private OrderProductsDTO fetchOrderProducts(OrderProductEntity orderProductEntity) {
        OrderProductsDTO dto = new OrderProductsDTO();
        dto.setProductId(orderProductEntity.getProduct().getId());
        dto.setProductName(orderProductEntity.getProduct().getProductName());
        dto.setUnitOfMeasurement(orderProductEntity.getProduct().getUnitOfMeasurement());
        dto.setProductCount(orderProductEntity.getCountProduct());
        dto.setProductAccepted(orderProductEntity.getAccepted());
        dto.setProductPrice(orderProductEntity.getPriceProduct());
        return dto;
    }

    private OrderDTO fetchOrderDto(OrderEntity orderEntity) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(orderEntity.getId());
        dto.setRegisterNumber(orderEntity.getRegisterNumber());
        dto.setStatus(orderEntity.getStatus());
        dto.setFirmId(orderEntity.getFirmEntity().getId());
        dto.setFirmName(orderEntity.getFirmEntity().getFirmName());
        dto.setShopId(orderEntity.getShopEntity().getId());
        dto.setShopName(orderEntity.getShopEntity().getShopName());
        dto.setCount(orderEntity.getOrderedProductsCount());
        dto.setPaymentType(orderEntity.getPaymentType());
        dto.setRegionId(orderEntity.getShopEntity().getRegion().getId());
        dto.setRegionName(orderEntity.getShopEntity().getRegion().getName());
        return dto;
    }
}
