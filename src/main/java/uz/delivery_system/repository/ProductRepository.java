package uz.delivery_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import uz.delivery_system.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    List<ProductEntity> findByFirmIdAndCategoryIdOrderByIdDesc(Long firmId, Long categoryId);

    Page<ProductEntity> findByFirmId(Long id, Pageable pageable);
}
