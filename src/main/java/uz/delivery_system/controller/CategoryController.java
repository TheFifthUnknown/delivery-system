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

@Api(description = "Categories")
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "create", notes = "Создание категории.")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated CategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
        return ResponseEntity.ok("Добавлена категория");
    }

    @ApiOperation(value = "update", notes = "Обновите существующую категорию.")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> update(@RequestBody @Validated CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return ResponseEntity.ok("Категория обновлена");
    }

    @ApiOperation(value = "delete", notes = "Удалить существующую категорию.")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Категория удалена");
    }

    @ApiOperation(value = "list of categories", notes = "Просмотр существующей категории.")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CategoryDTO>> listCategory() {
        List<CategoryDTO> dto = categoryService.listCategories();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
