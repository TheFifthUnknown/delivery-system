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
            throw new NullPointerException(1, "Id not specified");
        }
        CategoryEntity categoryEntity = categoryRepository.findOne(categoryDTO.getId());
        if (categoryEntity == null) {
            throw new NotFoundException(1,"There is no such category");
        }
        BeanUtils.copyProperties(categoryDTO, categoryEntity);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void delete(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findOne(id);
        if (categoryEntity == null) {
            throw new NotFoundException(1,"There is no such category");
        }
        categoryRepository.delete(categoryEntity);
    }

    @Override
    public List<CategoryDTO> listCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTO> list = new ArrayList<>();
        categoryEntities.forEach(categoryEntity -> {
            list.add(getCategoryDTO(categoryEntity));
        });
        return list;
    }

    private CategoryDTO getCategoryDTO(CategoryEntity categoryEntity) {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(categoryEntity, dto);
        return dto;
    }
}
