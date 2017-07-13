package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.CategoryEntity;

/**
 * Created by Nodirbek on 13.07.2017.
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
