package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.OrderEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByShopEntityIdOrderByIdDesc(Long id);

    OrderEntity findByIdAndShopEntityId(Long id, Long shopId);

    List<OrderEntity> findByFirmEntityIdOrderByIdDesc(Long id);

    OrderEntity findByIdAndFirmEntityId(Long id, Long firmId);

    List<OrderEntity> findByFirmEntityIdAndOrderedDateAfter(Long id, Date date);

    List<OrderEntity> findByFirmEntityIdAndStatusAndDeliverDateAfterAndDeliverDateBefore(Long id, short status, Date lowerBound, Date upperBound);

    List<OrderEntity> findByShopEntityIdAndStatusAndDeliverDateAfterAndDeliverDateBefore(Long id, short status, Date lowerBount, Date upperBound);

    List<OrderEntity> findByShopEntityIdAndFirmEntityIdAndStatusAndDeliverDateAfterAndDeliverDateBefore(Long shopId, Long firmId, short status, Date lowerBound, Date upperBound);
}
