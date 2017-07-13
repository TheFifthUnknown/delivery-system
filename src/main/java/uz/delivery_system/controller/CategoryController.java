package uz.delivery_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.delivery_system.dto.category.CategoryDTO;
import uz.delivery_system.dto.firm.FirmRegistrationDTO;
import uz.delivery_system.entity.FirmEntity;
import uz.delivery_system.service.CategoryService;

import java.net.URI;

/**
 * Created by Nodirbek on 13.07.2017.
 */
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity create(@RequestBody @Validated CategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
        return ResponseEntity.ok("Kategoriya qo'shildi");
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@RequestBody @Validated CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return ResponseEntity.ok("Kategoriya yangilandi");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Kategoriya o'chirildi");
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> listCategory(Pageable pageable) {
        Page<CategoryDTO> dto = categoryService.listCategories(pageable);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
