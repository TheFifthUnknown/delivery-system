package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.RegionEntity;


public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

}
