package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.CategoryEntity;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
