package uz.delivery_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.delivery_system.dto.report.PaymentType;
import uz.delivery_system.dto.report.ShortReportDTO;
import uz.delivery_system.entity.OrderEntity;
import uz.delivery_system.entity.UserEntity;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.repository.OrderRepository;
import uz.delivery_system.repository.UserRepository;
import uz.delivery_system.service.ReportService;
import uz.delivery_system.utils.SecurityUtils;

import java.util.*;

/**
 * Created by Nodirbek on 04.10.2017.
 */
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
            throw new NotFoundException(1,"Firma logini bilan kiring");
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
            throw new NotFoundException(1,"Firma logini bilan kiring");
        }
        Date month = thisMonth();
        List<OrderEntity> list = orderRepository.findByFirmEntityIdAndOrderedDateAfter(userEntity.getFirm().getId(), month);
        ShortReportDTO dto = builtShortReportDto(list);
        return dto;
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

    private void increase(PaymentType paymentType, int payment, Integer cost) {
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
