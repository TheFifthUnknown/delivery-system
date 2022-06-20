package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.OrderProductEntity;


public interface OrderProductsRepository extends JpaRepository<OrderProductEntity, Long> {

    OrderProductEntity findByOrderIdAndProductId(Long id, Long productId);
}
