package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.OrderEntity;

import java.util.List;

/**
 * Created by Nodirbek on 31.08.2017.
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByShopEntityIdOrderByIdDesc(Long id);

    OrderEntity findByIdAndShopEntityId(Long id, Long shopId);

    List<OrderEntity> findByFirmEntityIdOrderByIdDesc(Long id);

    OrderEntity findByIdAndFirmEntityId(Long id, Long firmId);
}
