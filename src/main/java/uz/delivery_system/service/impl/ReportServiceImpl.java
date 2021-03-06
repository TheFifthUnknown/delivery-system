package uz.delivery_system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.delivery_system.dto.report.*;
import uz.delivery_system.entity.*;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.repository.OrderRepository;
import uz.delivery_system.repository.UserRepository;
import uz.delivery_system.service.ReportService;
import uz.delivery_system.utils.SecurityUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ShortReportDTO getDailyReport() {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        if (userEntity.getFirm() == null) {
            throw new NotFoundException(1,"Log in with the company log");
        }
        Date today = yesterday();
        List<OrderEntity> list = orderRepository.findByFirmEntityIdAndOrderedDateAfter(userEntity.getFirm().getId(), today);
        ShortReportDTO dto = builtShortReportDto(list);
        return dto;
    }

    @Override
    public ShortReportDTO getMonthlyReport() {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        if (userEntity.getFirm() == null) {
            throw new NotFoundException(1,"Log in with the company log");
        }
        Date month = thisMonth();
        List<OrderEntity> list = orderRepository.findByFirmEntityIdAndOrderedDateAfter(userEntity.getFirm().getId(), month);
        ShortReportDTO dto = builtShortReportDto(list);
        return dto;
    }

    @Override
    public List<ProductsReportDTO> getReportByProducts(PeriodOfTimeDTO dto) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        if (userEntity.getFirm() == null) {
            throw new NotFoundException(1,"Log in with the company log");
        }
        dto.getLowerBound().add(Calendar.DATE, -1);
        dto.getUpperBound().add(Calendar.DATE, +1);
        List<OrderEntity> list
                = orderRepository.findByFirmEntityIdAndStatusAndDeliverDateAfterAndDeliverDateBefore(userEntity.getFirm().getId(), (short)3, dto.getLowerBound().getTime(), dto.getUpperBound().getTime());
        Map<Long, ProductsReportDTO> map = getMappedProducts(list);
        return map.values().stream().collect(Collectors.toList());
    }

    private Map<Long, ProductsReportDTO> getMappedProducts(List<OrderEntity> list) {
        Map<Long, ProductsReportDTO> map = new HashMap<>();
        for (OrderEntity orderEntity : list) {
            for (OrderProductEntity orderProductEntity : orderEntity.getOrderProducts() ) {
                if(!orderProductEntity.getAccepted()){
                    continue;
                }
                ProductEntity product = orderProductEntity.getProduct();
                if(!map.containsKey(product.getId())){
                    ProductsReportDTO dto = new ProductsReportDTO();
                    BeanUtils.copyProperties(product, dto);
                    dto.setProductId(product.getId());
                    map.put(product.getId(), dto);
                }
                ProductsReportDTO dto = map.get(product.getId());
                long sumProductInOrder = orderProductEntity.getPriceProduct() * orderProductEntity.getCountProduct();
                dto.chargeTotalPrice(sumProductInOrder);
                dto.chargeProductCount(orderProductEntity.getCountProduct());
                if (orderEntity.getPaymentType() == 0){
                    dto.chargeCash(sumProductInOrder);
                }
                if (orderEntity.getPaymentType() == 1){
                    dto.chargeCards(sumProductInOrder);
                }
                if (orderEntity.getPaymentType() == 2){
                    dto.chargeAccaunt(sumProductInOrder);
                }
                map.put(product.getId(), dto);
            }
        }
        return map;
    }

    @Override
    public List<ShopsReportDTO> getReportByShops(PeriodOfTimeDTO dto) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        if (userEntity.getFirm() == null) {
            throw new NotFoundException(1,"Log in with the company log");
        }
        dto.getLowerBound().add(Calendar.DATE, -1);
        dto.getUpperBound().add(Calendar.DATE, +1);
        List<OrderEntity> list
                = orderRepository.findByFirmEntityIdAndStatusAndDeliverDateAfterAndDeliverDateBefore(userEntity.getFirm().getId(), (short)3, dto.getLowerBound().getTime(), dto.getUpperBound().getTime());
        Map<Long, ShopsReportDTO> map = getMappedShops(list);
        return map.values().stream().collect(Collectors.toList());
    }

    private Map<Long, ShopsReportDTO> getMappedShops(List<OrderEntity> list) {
        Map<Long, ShopsReportDTO> map = new HashMap<>();
        for (OrderEntity orderEntity : list) {
            ShopEntity shopEntity = orderEntity.getShopEntity();
            if(!map.containsKey(shopEntity.getId())){
                ShopsReportDTO dto = new ShopsReportDTO();
                BeanUtils.copyProperties(shopEntity, dto);
                dto.setShopId(shopEntity.getId());
                map.put(shopEntity.getId(), dto);
            }
            ShopsReportDTO dto = map.get(shopEntity.getId());
            dto.increaseOrderCount();
            dto.chargeTotalPrice(orderEntity.getOrderedProductsCost());
            if (orderEntity.getPaymentType() == 0){
                dto.chargeCash(orderEntity.getOrderedProductsCost());
            }
            if (orderEntity.getPaymentType() == 1){
                dto.chargeCards(orderEntity.getOrderedProductsCost());
            }
            if (orderEntity.getPaymentType() == 2){
                dto.chargeAccaunt(orderEntity.getOrderedProductsCost());
            }
            map.put(shopEntity.getId(), dto);
        }
        return map;
    }

    @Override
    public List<FirmsReportDTO> getReportByFirms(PeriodOfTimeDTO dto) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        if (userEntity.getShop() == null) {
            throw new NotFoundException(1,"Log in with the store log");
        }
        dto.getLowerBound().add(Calendar.DATE, -1);
        dto.getUpperBound().add(Calendar.DATE, +1);
        List<OrderEntity> list
                = orderRepository.findByShopEntityIdAndStatusAndDeliverDateAfterAndDeliverDateBefore(userEntity.getShop().getId(), (short)3, dto.getLowerBound().getTime(), dto.getUpperBound().getTime());
        Map<Long, FirmsReportDTO> map = getMappedFirms(list);
        return map.values().stream().collect(Collectors.toList());
    }

    private Map<Long, FirmsReportDTO> getMappedFirms(List<OrderEntity> list) {
        Map<Long, FirmsReportDTO> map = new HashMap<>();
        for (OrderEntity orderEntity : list) {
            FirmEntity  firmEntity = orderEntity.getFirmEntity();
            if(!map.containsKey(firmEntity.getId())){
                FirmsReportDTO dto = new FirmsReportDTO();
                BeanUtils.copyProperties(firmEntity, dto);
                dto.setFirmId(firmEntity.getId());
                map.put(firmEntity.getId(), dto);
            }
            FirmsReportDTO dto = map.get(firmEntity.getId());
            dto.increaseOrderCount();
            dto.chargeTotalPrice(orderEntity.getOrderedProductsCost());
            if (orderEntity.getPaymentType() == 0){
                dto.chargeCash(orderEntity.getOrderedProductsCost());
            }
            if (orderEntity.getPaymentType() == 1){
                dto.chargeCards(orderEntity.getOrderedProductsCost());
            }
            if (orderEntity.getPaymentType() == 2){
                dto.chargeAccaunt(orderEntity.getOrderedProductsCost());
            }
            map.put(firmEntity.getId(), dto);
        }
        return map;
    }

    @Override
    public List<ProductsReportDTO> getReportByFirmProducts(Long firmId, PeriodOfTimeDTO dto) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        if (userEntity.getShop() == null) {
            throw new NotFoundException(1,"Log in with the store log");
        }
        dto.getLowerBound().add(Calendar.DATE, -1);
        dto.getUpperBound().add(Calendar.DATE, +1);
        List<OrderEntity> list
                = orderRepository.findByShopEntityIdAndFirmEntityIdAndStatusAndDeliverDateAfterAndDeliverDateBefore(userEntity.getShop().getId(), firmId, (short)3, dto.getLowerBound().getTime(), dto.getUpperBound().getTime());
        Map<Long, ProductsReportDTO> map = getMappedProducts(list);
        return map.values().stream().collect(Collectors.toList());
    }

    private ShortReportDTO builtShortReportDto(List<OrderEntity> list) {
        ShortReportDTO dto = new ShortReportDTO();
        for (OrderEntity orderEntity : list) {
            switch (orderEntity.getStatus()){
                case 0:
                    increase(dto.getWaiting(), orderEntity.getPaymentType(), orderEntity.getOrderedProductsCost());
                    break;
                case 2:
                    increase(dto.getAccepted(), orderEntity.getPaymentType(), orderEntity.getOrderedProductsCost());
                    break;
                case 3:
                    increase(dto.getDelivered(), orderEntity.getPaymentType(), orderEntity.getOrderedProductsCost());
                    break;
            }
        }
        return dto;
    }

    private void increase(PaymentType paymentType, int payment, long cost) {
        if (payment == 0){
            paymentType.chargeCash(cost);
        }
        if(payment == 1){
            paymentType.chargeCards(cost);
        }
        if(payment == 2){
            paymentType.chargeAccount(cost);
        }
    }

    private static Date yesterday() {
        Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONDAY);
        int year = calendar.get(Calendar.YEAR);
        Calendar date = new GregorianCalendar(year,month,day);
        date.add(Calendar.DATE, -1);
        System.out.println(date);
        return date.getTime();
    }

    private static Date thisMonth() {
        Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
        int month = calendar.get(Calendar.MONDAY);
        int year = calendar.get(Calendar.YEAR);
        Calendar date = new GregorianCalendar(year,month,1);
        date.add(Calendar.DATE, -1);
        System.out.println(date);
        return date.getTime();
    }

}
