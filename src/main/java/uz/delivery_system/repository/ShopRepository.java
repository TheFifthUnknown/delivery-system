package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.ShopEntity;

/**
 * Created by Nodirbek on 12.07.2017.
 */
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

}
