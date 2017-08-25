package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.ShopEntity;

import java.util.Optional;

/**
 * Created by Nodirbek on 12.07.2017.
 */
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    Optional<ShopEntity> findByShopINN(String shopINN);

    Long countByShopINN(String shopINN);
}
