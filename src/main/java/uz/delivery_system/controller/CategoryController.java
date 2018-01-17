package uz.delivery_system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;

/**
 * Created by Nodirbek on 13.07.2017.
 * Categoriyalarni boshqarish.
 * tizimga kiritilgan firmalar ushbu kategoriyalar bo'yicha qo'shiladi.
 */
@Api(description = "Kategoriyalar")
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "yaratish", notes = "Kategoriya yaratish.")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated CategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
        return ResponseEntity.ok("Kategoriya qo'shildi");
    }

    @ApiOperation(value = "yangilash", notes = "Mavjud kategoriyani yangilash.")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> update(@RequestBody @Validated CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return ResponseEntity.ok("Kategoriya yangilandi");
    }

    @ApiOperation(value = "o'chirish", notes = "Mavjud kategoriyani o'chirish.")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Kategoriya o'chirildi");
    }

    @ApiOperation(value = "kategoriyalar ro'yhati", notes = "Mavjud kategoriyani ko'rish.")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CategoryDTO>> listCategory() {
        List<CategoryDTO> dto = categoryService.listCategories();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
