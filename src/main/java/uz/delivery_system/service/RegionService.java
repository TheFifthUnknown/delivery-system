package uz.delivery_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.delivery_system.dto.region.RegionDTO;

/**
 * Created by Nodirbek on 13.07.2017.
 */
public interface RegionService {
    void create(RegionDTO regionDTO);

    void update(RegionDTO regionDTO);

    void delete(Long id);

    Page<RegionDTO> listCategories(Pageable pageable);
}
