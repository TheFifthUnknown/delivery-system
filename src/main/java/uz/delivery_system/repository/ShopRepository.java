package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.ShopEntity;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    Optional<ShopEntity> findByShopINN(String shopINN);

    Long countByShopINN(String shopINN);
}
