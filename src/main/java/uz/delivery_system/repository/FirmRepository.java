package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.FirmEntity;

import java.util.List;


public interface FirmRepository extends JpaRepository<FirmEntity, Long>{

    List<FirmEntity> findAllByOrderByFirmPriorityDesc();
}
