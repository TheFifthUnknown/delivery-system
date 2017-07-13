package uz.delivery_system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.delivery_system.dto.region.RegionDTO;
import uz.delivery_system.entity.RegionEntity;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.exceptions.NullPointerException;
import uz.delivery_system.repository.RegionRepository;
import uz.delivery_system.service.RegionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nodirbek on 13.07.2017.
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public void create(RegionDTO regionDTO) {
        RegionEntity regionEntity = new RegionEntity();
        BeanUtils.copyProperties(regionDTO, regionEntity, "id");
        regionRepository.save(regionEntity);
    }

    @Override
    public void update(RegionDTO regionDTO) {
        if(regionDTO.getId() == null){
            throw new NullPointerException(1, "Id ko'rsatilmagan");
        }
        RegionEntity categoryEntity = regionRepository.findOne(regionDTO.getId());
        if (categoryEntity == null) {
            throw new NotFoundException(1,"Bunday kategoriya mavjud emas");
        }
        BeanUtils.copyProperties(regionDTO, categoryEntity);
        regionRepository.save(categoryEntity);
    }

    @Override
    public void delete(Long id) {
        RegionEntity categoryEntity = regionRepository.findOne(id);
        if (categoryEntity == null) {
            throw new NotFoundException(1,"Bunday kategoriya mavjud emas");
        }
        regionRepository.delete(categoryEntity);
    }

    @Override
    public Page<RegionDTO> listCategories(Pageable pageable) {
        Page<RegionEntity> page = regionRepository.findAll(pageable);
        List<RegionDTO> list = new ArrayList<>();
        page.forEach(categoryEntity -> {
            list.add(getRegionDTO(categoryEntity));
        });
        return new PageImpl<RegionDTO>(list);
    }

    private RegionDTO getRegionDTO(RegionEntity regionEntity) {
        RegionDTO regionDTO = new RegionDTO();
        BeanUtils.copyProperties(regionEntity, regionDTO);
        return regionDTO;
    }
}
