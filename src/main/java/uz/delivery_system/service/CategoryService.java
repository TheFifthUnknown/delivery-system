package uz.delivery_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.delivery_system.dto.category.CategoryDTO;

import java.util.List;

/**
 * Created by Nodirbek on 13.07.2017.
 */
public interface CategoryService {

    void create(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void delete(Long id);

    List<CategoryDTO> listCategories();
}
