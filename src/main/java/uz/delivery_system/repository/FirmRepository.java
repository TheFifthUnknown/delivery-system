package uz.delivery_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.delivery_system.entity.FirmEntity;

import java.util.List;

/**
 * Created by Nodirbek on 07.07.2017.
 */
public interface FirmRepository extends JpaRepository<FirmEntity, Long>{

    List<FirmEntity> findAllByOrderByFirmPriorityDesc();
}
