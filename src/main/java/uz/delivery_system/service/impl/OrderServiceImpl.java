package uz.delivery_system.service.impl;

import org.springframework.beans.BeanUtils;
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
            throw new NotFoundException(1, "Log in with the store user logger");
        }
        FirmEntity firmEntity = firmRepository.findOne(dto.getFirmId());
        if (firmEntity == null) {
            throw new NotFoundException(1,"Such a firm does not exist!");
        }
        OrderEntity orderEntity = new OrderEntity();
        List<OrderProductEntity> orderProductEntities = new ArrayList<>();
        long totalSum = 0;
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
            productEntity.setAmountInPending(productEntity.getAmountInPending()+productCount.getCount());
            orderProductEntities.add(orderProductEntity);
            productRepository.save(productEntity);
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
            throw new NotFoundException(1,"Log in with your store or firm log!");
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
            throw new NotFoundException(1, "Such an order could not be found");
        }
        List<OrderProductsDTO> list = new ArrayList<>();
        orderEntity.getOrderProducts().forEach(orderProductEntity -> {
            list.add(fetchOrderProducts(orderProductEntity));
        });
        return list;
    }

    @Override
    @Transactional
    public String changeStatus(Long id, short status) {
        OrderEntity orderEntity = orderRepository.findOne(id);
        short currentStatus = orderEntity.getStatus();
        String message = currentStatus != status ? "Your order can not be transferred to this status!":
                "The order stands in this status from the beginning!";
        if(currentStatus == 0){
            if(status == 1){
                message = "Order canceled!";
                orderEntity.setStatus(status);
                decreaseProductsAmountInPending(orderEntity);
            }
            if(status == 2){
                message = "Order accepted!";
                orderEntity.setStatus(status);
                chargeProductsAmountFromPendingToStore(orderEntity);
            }
        }else if(currentStatus == 2){
            if(status == 3){
                message = "Confirmed order delivery!";
                orderEntity.setStatus(status);
                orderEntity.setDeliverDate(new Date());
                decreaseProductsAmountInOrder(orderEntity);
            }
        }
        orderRepository.save(orderEntity);
        return message;
    }

    @Override
    @Transactional
    public String revertOrder(Long id) {
        OrderEntity orderEntity = orderRepository.findOne(id);
        if(orderEntity == null){
            throw new NotFoundException(1,"Such an order is not available");
        }
        if (orderEntity.getStatus() != 2){
            return "Only orders with accepted status can be returned";
        }
        chargeProductsAmountFromOrderToStore(orderEntity);
        orderEntity.setStatus((short)4);
        orderRepository.save(orderEntity);
        return "Order returned";
    }

    private void chargeProductsAmountFromOrderToStore(OrderEntity orderEntity) {
        for (OrderProductEntity orderProductEntity : orderEntity.getOrderProducts()) {
            if(orderProductEntity.getAccepted()){
                ProductEntity productEntity = orderProductEntity.getProduct();
                productEntity.setAmountInOrder(productEntity.getAmountInOrder() - orderProductEntity.getCountProduct());
                productEntity.setAmountInStore(productEntity.getAmountInStore() + orderProductEntity.getCountProduct());
                productRepository.save(productEntity);
            }
        }
    }

    private void decreaseProductsAmountInOrder(OrderEntity orderEntity) {
        for (OrderProductEntity orderProductEntity : orderEntity.getOrderProducts()) {
            if(orderProductEntity.getAccepted()){
                ProductEntity productEntity = orderProductEntity.getProduct();
                productEntity.setAmountInOrder(productEntity.getAmountInOrder() - orderProductEntity.getCountProduct());
                productRepository.save(productEntity);
            }
        }
    }

    private void chargeProductsAmountFromPendingToStore(OrderEntity orderEntity) {
        for (OrderProductEntity orderProductEntity : orderEntity.getOrderProducts()) {
            if(orderProductEntity.getAccepted()){
                ProductEntity productEntity = orderProductEntity.getProduct();
                productEntity.setAmountInPending(productEntity.getAmountInPending() - orderProductEntity.getCountProduct());
                productEntity.setAmountInStore(productEntity.getAmountInStore() - orderProductEntity.getCountProduct());
                productEntity.setAmountInOrder(productEntity.getAmountInOrder() + orderProductEntity.getCountProduct());
                productRepository.save(productEntity);
            }
        }
    }

    private void decreaseProductsAmountInPending(OrderEntity orderEntity) {
        for (OrderProductEntity orderProductEntity : orderEntity.getOrderProducts()) {
            if(orderProductEntity.getAccepted()){
                ProductEntity productEntity = orderProductEntity.getProduct();
                productEntity.setAmountInPending(productEntity.getAmountInPending() - orderProductEntity.getCountProduct());
                productRepository.save(productEntity);
            }
        }
    }

    @Override
    @Transactional
    public String acceptProduct(Long id, Long productId, Boolean accepted) {
        OrderProductEntity orderProductEntity =
                orderProductsRepository.findByOrderIdAndProductId(id,productId);
        if (orderProductEntity == null) {
            throw new NotFoundException(1, "This order did not find any products from the show");
        }

        if(orderProductEntity.getAccepted() != accepted){
            orderProductEntity.setAccepted(accepted);
            ProductEntity productEntity = orderProductEntity.getProduct();
            long sum = orderProductEntity.getCountProduct() * orderProductEntity.getPriceProduct();
            if(!accepted){
                productEntity.setAmountInPending(productEntity.getAmountInPending()-orderProductEntity.getCountProduct());
                orderProductEntity.getOrder().setOrderedProductsCost(orderProductEntity.getOrder().getOrderedProductsCost()-sum);
            }else{
                productEntity.setAmountInPending(productEntity.getAmountInPending()+orderProductEntity.getCountProduct());
                orderProductEntity.getOrder().setOrderedProductsCost(orderProductEntity.getOrder().getOrderedProductsCost()+sum);
            }
            orderProductsRepository.save(orderProductEntity);
            productRepository.save(productEntity);
        }

        String message = "Products buyutma"+(accepted ? "ga joined for delivery" :
                " removed from the list of products");
        return message;
    }


    private OrderProductsDTO fetchOrderProducts(OrderProductEntity orderProductEntity) {
        OrderProductsDTO dto = new OrderProductsDTO();
        dto.setProductId(orderProductEntity.getProduct().getId());
        dto.setProductCode(orderProductEntity.getProduct().getProductCode());
        dto.setProductName(orderProductEntity.getProduct().getProductName());
        dto.setUnitOfMeasurement(orderProductEntity.getProduct().getUnitOfMeasurement());
        dto.setProductAmountInStore(orderProductEntity.getProduct().getAmountInStore());
        dto.setProductCount(orderProductEntity.getCountProduct());
        dto.setProductAccepted(orderProductEntity.getAccepted());
        dto.setProductPrice(orderProductEntity.getPriceProduct());
        return dto;
    }

    private OrderDTO fetchOrderDto(OrderEntity orderEntity) {
        OrderDTO dto = new OrderDTO();
        BeanUtils.copyProperties(orderEntity, dto);
        dto.setOrderId(orderEntity.getId());
//        dto.setRegisterNumber(orderEntity.getRegisterNumber());
//        dto.setStatus(orderEntity.getStatus());
//        dto.setPaymentType(orderEntity.getPaymentType());
        dto.setFirmId(orderEntity.getFirmEntity().getId());
        dto.setFirmName(orderEntity.getFirmEntity().getFirmName());
        dto.setShopId(orderEntity.getShopEntity().getId());
        dto.setShopName(orderEntity.getShopEntity().getShopName());
        dto.setCount(orderEntity.getOrderedProductsCount());
        dto.setRegionId(orderEntity.getShopEntity().getRegion().getId());
        dto.setRegionName(orderEntity.getShopEntity().getRegion().getName());
        return dto;
    }
}
