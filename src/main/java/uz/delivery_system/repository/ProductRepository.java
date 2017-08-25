package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.ProductEntity;

/**
 * Created by Nodirbek on 15.07.2017.
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


}
