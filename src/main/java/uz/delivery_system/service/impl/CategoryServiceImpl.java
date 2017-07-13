package uz.delivery_system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.delivery_system.dto.category.CategoryDTO;
import uz.delivery_system.entity.CategoryEntity;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.exceptions.NullPointerException;
import uz.delivery_system.repository.CategoryRepository;
import uz.delivery_system.service.CategoryService;
import uz.delivery_system.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nodirbek on 13.07.2017.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(categoryDTO, categoryEntity, "id");
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        if(categoryDTO.getId() == null){
            throw new NullPointerException(1, "Id ko'rsatilmagan");
        }
        CategoryEntity categoryEntity = categoryRepository.findOne(categoryDTO.getId());
        if (categoryEntity == null) {
            throw new NotFoundException(1,"Bunday kategoriya mavjud emas");
        }
        BeanUtils.copyProperties(categoryDTO, categoryEntity);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void delete(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findOne(id);
        if (categoryEntity == null) {
            throw new NotFoundException(1,"Bunday kategoriya mavjud emas");
        }
        categoryRepository.delete(categoryEntity);
    }

    @Override
    public Page<CategoryDTO> listCategories(Pageable pageable) {
        Page<CategoryEntity> page = categoryRepository.findAll(pageable);
        List<CategoryDTO> list = new ArrayList<>();
        page.forEach(categoryEntity -> {
            list.add(getCategoryDTO(categoryEntity));
        });
        return new PageImpl<CategoryDTO>(list);
    }

    private CategoryDTO getCategoryDTO(CategoryEntity categoryEntity) {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(categoryEntity, dto);
        return dto;
    }
}
