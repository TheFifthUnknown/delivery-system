package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.OrderProductEntity;

/**
 * Created by Nodirbek on 03.09.2017.
 */
public interface OrderProductsRepository extends JpaRepository<OrderProductEntity, Long> {

    OrderProductEntity findByOrderIdAndProductId(Long id, Long productId);
}
